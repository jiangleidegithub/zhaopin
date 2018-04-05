package com.jianglei.zhaopin.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jianglei.zhaopin.model.Company;
import com.jianglei.zhaopin.model.Job;


public class ZhiLianUtils {
	public static void main(String[] args) throws Exception {
		String url = "http://sou.zhaopin.com/jobs/searchresult.ashx?in=210500%3b160400%3b160000&jl=%E9%95%BF%E6%98%A5&kw=java&isadv=0&sf=4001&st=6000&ispts=1&isfilter=1&p=1&bj=160000&sj=2040";
		List<Job> list = getJobsAndCompanys(url);
		for (Job job : list) {
			System.out.println(job);
		}
		/*
		 * List<String> details = getAllJobDetails(url);
		 * System.out.println(details.size()); List<String> coords =
		 * getAllCoords(details);
		 */

	}

	/**
	 * 解析每一页搜索结果列表页的内容，解析出职位名称，职位月薪，职位详情页url， 公司详情页url 工作地址 工作地址的经纬度 返回List<Job>
	 * 
	 * @param URL
	 *            搜索结果列表页的url
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static List<Job> getJobsAndCompanys(String URL) throws Exception {
		URL = URL.replace("&p=1", "");
		List<Job> list = new ArrayList<Job>();
		Document doc = getDoc(URL);
		Integer pageSize = getPageSize(doc);
		System.out.println("结果页共：" + pageSize + "页");
		if (pageSize != null && pageSize > 0) {
			for (int i = 1; i <= pageSize; i++) {
				String tempURL = URL + "&p=" + i;
				// 单页的doc
				Document tmpDoc = getDoc(tempURL);
				Elements tables = tmpDoc.getElementsByClass("newlist");
				for (Element table : tables) {
					Job job = new Job();
					Company company = new Company();
					try {
						String jobName = table.getElementsByAttribute("par").get(0).html();
						String jobUrl = table.getElementsByAttribute("par").get(0).attr("href");
						jobName = jobName.replace("<b>", "").replace("</b>", "");
						String companyName = table.getElementsByClass("gsmc").get(0).getElementsByTag("a").html();
						String companyUrl = table.getElementsByClass("gsmc").get(0).getElementsByTag("a").attr("href");
						String jobSalary = table.getElementsByClass("zwyx").get(0).html();
						String jobAddress = table.getElementsByClass("gzdd").get(0).html();
						String pubDate = table.getElementsByClass("gxsj").get(0).getElementsByTag("span").html();
						job.setJobSalary(jobSalary);
						job.setJobName(jobName);
						job.setJobUrl(jobUrl);
						job.setJobPosition(jobAddress);
						company.setCompanyName(companyName);
						company.setCompanyUrl(companyUrl);
						Document jobDoc = getDoc(jobUrl);
						// 有坐标的情况
						Elements maps = doc.getElementsByClass("see-map");
						if (maps != null && maps.size() > 0) {
							// 取得坐标
							String hr = maps.get(0).attr("href");
							String str1 = hr.substring(hr.lastIndexOf("','") + 3);
							String x = str1.substring(0, str1.lastIndexOf("'"));
							String y = hr.substring(hr.lastIndexOf("','") - 9, hr.lastIndexOf("','"));
							String lonlat = x + "," + y;
							// 坐标转换
							String baiduCoord = "";
							try {
								System.out.println("转换前" + lonlat);
								baiduCoord = CoordsConverter.convert(lonlat);
								System.out.println("转换后" + baiduCoord);
								String jobDetailAddr = jobDoc.getElementsByTag("h2").get(1).ownText();
								job.setJobAddress(jobDetailAddr);
								job.setJobLon(Double.parseDouble(baiduCoord.substring(0, baiduCoord.lastIndexOf(","))));
								job.setJobLat(Double.parseDouble(baiduCoord.substring(baiduCoord.lastIndexOf(",") + 1)));
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("坐标转换出现问题");
							} finally {
								/*
								 * String xx = baiduCoord.substring(0,
								 * baiduCoord.lastIndexOf(",")); String yy =
								 * baiduCoord
								 * .substring(baiduCoord.lastIndexOf(",") + 1);
								 * Double lon = Double.parseDouble(xx); Double
								 * lat = Double.parseDouble(yy);
								 */
							}
						} else {
							// 没有坐标的情况
							String jobDetailAddr = jobDoc.getElementsByTag("h2").get(1).ownText();
							job.setJobAddress(jobDetailAddr);
							// 进行地址编码,解析出经纬度
							String baiduCoord = CoordsConverter.convertAddtoCoords(jobDetailAddr);
							if (!baiduCoord.equals("")) {
								job.setJobLon(Double.parseDouble(baiduCoord.substring(0, baiduCoord.lastIndexOf(","))));
								job.setJobLat(Double.parseDouble(baiduCoord.substring(baiduCoord.lastIndexOf(",") + 1)));
							}
						}
						job.setCompany(company);
						list.add(job);
					} catch (Exception e) {
						System.out.println("解析列表页出现问题：" + tempURL);
						e.printStackTrace();
					} finally {
						continue;
					}
				}
			}
		}
		return list;
	}

	/**
	 * 给筛选条件首页的Url 返回所有job的详情页URL
	 * 
	 * @param URL
	 * @return List<String>
	 * @throws Exception
	 */
	public static List<String> getAllJobDetails(String URL) throws Exception {
		List<String> list = new ArrayList<String>();
		Document doc = getDoc(URL);
		// 计算页数
		Integer pageSize = getPageSize(doc);
		System.out.println(pageSize);
		if (pageSize != null && pageSize > 0) {
			for (int i = 1; i <= pageSize; i++) {
				String tempURL = URL + "&p=" + i;
				Document tempDoc = getDoc(tempURL);
				List<String> details = getDetails(tempDoc);
				list.addAll(details);
			}
		}
		return list;
	}

	/**
	 * 获取总页数
	 * 
	 * @param doc
	 * @return
	 */
	public static Integer getPageSize(Document doc) {
		int totalCount = Integer.parseInt(doc.getElementsByClass("search_yx_tj").get(0).getElementsByTag("em").html());
		Integer pageSize;
		if (totalCount % 60 != 0) {
			pageSize = (int) Math.ceil(totalCount / 60) + 1;
		} else {
			pageSize = (int) Math.floor(totalCount / 60);
		}
		return pageSize;
	}

	/**
	 * 从筛选每页中拿出每个详情页的链接 拿出一页的数据60个
	 * 
	 * @param doc
	 * @return
	 */
	public static List<String> getDetails(Document doc) {
		List<String> list = new ArrayList<String>();
		Elements as = doc.getElementsByAttribute("par");
		for (Element a : as) {
			String href = a.attr("href");
			if (href != null && !href.equals("")) {
				list.add(href);
			}
		}
		return list;
	}

	/**
	 * 获得文档对象
	 * 
	 * @param URL
	 * @return
	 */
	public static Document getDoc(String URL) {
		Document doc = null;
		try {
			Connection connection = HttpConnection.connect(URL);
			connection.timeout(60000);
			doc = connection.get();
		} catch (IOException e) {
			System.out.println("获取doc出现问题:url" + URL);
			e.printStackTrace();
		}
		return doc;
	}

	public static List<String> getAllCoords(List<String> detailUrls) {
		List<String> list = new ArrayList<String>();
		for (String href : detailUrls) {
			Document doc = getDoc(href);
			// 有坐标的情况
			Elements maps = doc.getElementsByClass("see-map");
			if (maps != null && maps.size() > 0) {
				// 取得坐标
				String hr = maps.get(0).attr("href");
				String str1 = hr.substring(hr.lastIndexOf("','") + 3);
				String x = str1.substring(0, str1.lastIndexOf("'"));
				String y = hr.substring(hr.lastIndexOf("','") - 9, hr.lastIndexOf("','"));
				String lonlat = x + "," + y;
				list.add(lonlat);
			}
		}
		System.out.println("去重前一共+" + list.size() + "条");
		list = removeSameAndConvert(list);
		System.out.println("去重后一共+" + list.size() + "条");
		return list;
	}

	/**
	 * 去除重复值,然后转换
	 * 
	 * @param list
	 * @param lonlat
	 */
	public static List<String> removeSameAndConvert(List<String> list) {
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (String lonlat : list) {
			if (set.add(lonlat)) {
				String baiduCoord = "";
				try {
					System.out.println("转换前" + lonlat);
					baiduCoord = CoordsConverter.convert(lonlat);
					System.out.println("转换后" + baiduCoord);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("坐标转换出现问题");
				} finally {
					/*
					 * String
					 * xx=baiduCoord.substring(0,baiduCoord.lastIndexOf(","));
					 * String
					 * yy=baiduCoord.substring(baiduCoord.lastIndexOf(",")+1);
					 */
					// LonLat lonLat=new LonLat(Double.parseDouble(xx),
					// Double.parseDouble(yy));
					if (!baiduCoord.equals("")) {
						newList.add(baiduCoord);

					}
				}
			}
		}
		return newList;
	}
}
