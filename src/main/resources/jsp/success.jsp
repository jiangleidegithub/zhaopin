<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Hello, World</title>
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#container {
	height: 100%;
	width: 100%;
	text-align: left;
	bottom: 0px
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GGgbgc3e0uWg4v15nXcaywRtIsWbBvTh"></script>
<script type="text/javascript" src="http://mapv.baidu.com/build/mapv.js"></script>
<script src="//cdn.bootcss.com/jquery/1.8.3/jquery.js"></script>
<!-- <script type="text/javascript">
	$(document).ready(function(){
		var map = new BMap.Map("container");          // 创建地图实例  
		var point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
		map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别  
		var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
		
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		//添加标注点json
		var json_data =${json};
		var pointArray = new Array();
		for(var i=0;i<json_data.length;i++){
			var str1=json_data[i]+"";
			var lon=str1.substring(0,str1.indexOf(','));
			var lat=str1.substring(str1.indexOf(',')+1);
				var marker = new BMap.Marker(new BMap.Point(lon, lat)); // 创建点
				map.addOverlay(marker); //增加点
			pointArray[i] = new BMap.Point(lon,lat);
			marker.addEventListener("click",attribute);
		}
		//让所有点在视野范围内
		map.setViewport(pointArray);
		//获取覆盖物位置
		function attribute(e){
			var p = e.target;
			alert("marker的位置是" + p.getPosition().lng + "," + p.getPosition().lat);    
		}
		
		
		
	});
</script> -->
</head>
<body>
	<div id="container"></div>
	<script type="text/javascript">
	$(document).ready(function(){
		  // 百度地图API功能
        var map = new BMap.Map("container", {
            enableMapClick: false
        });    // 创建Map实例
        map.centerAndZoom(new BMap.Point(105.403119, 38.028658), 5);  // 初始化地图,设置中心点坐标和地图级别
        map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
        var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     

        map.setMapStyle({
            style: 'light'
        });
        var data = [];
        //构造数据
        var json_data =${json};
		for(var i=0;i<json_data.length;i++){
			var lon=json_data[i].jobLon;
			var lat=json_data[i].jobLat;
			console.log(i);
			//显示info
			var myLabel = new BMap.Label(json_data[i].jobName+json_data[i].jobSalary, {offset:0,position:new BMap.Point(lon,lat)});	
			//myLabel.setTitle("我是文本标注label");              
			//为label添加鼠标提示
			myLabel.setStyle({                                   //给label设置样式，任意的CSS都是可以的
										    color:"red",                   //颜色
										    fontSize:"6px", });
			map.addOverlay(myLabel);    
			 data.push({
		            geometry: {
		                type: 'Point',
		                coordinates: [lon,lat],
			 			jobName:json_data[i].jobName,
			 			jobSalary:json_data[i].jobSalary,
			 			jobUrl:json_data[i].jobUrl,
			 			companyName:json_data[i].company.companyName,
			 			companyUrl:json_data[i].company.companyUrl,
			 			jobAddress:json_data[i].jobAddress
			 			
		            },
		            count: 30 * Math.random()
		        });
		}
		//设置数据
        var dataSet = new mapv.DataSet(data);

        var options = {
            fillStyle: 'rgba(255, 50, 50, 0.6)',
            shadowColor: 'rgba(255, 50, 50, 1)',
            shadowBlur: 30,
            globalCompositeOperation: 'lighter',
            methods: {
            	//点击事件
                click: function (item) {
                    console.log(item.geometry.coordinates);
                    console.log(item.geometry.jobName);
                    console.log(item.geometry.jobSalary);
                    console.log(item.geometry.jobUrl);
                    console.log(item.geometry.companyName);
                    console.log(item.geometry.companyUrl);
                    console.log(item.geometry.jobAddress);
                }
            },
            size: 5,
            draw: 'simple'
        }

        var mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);

        // dataSet.set(data); // 修改数据

        mapvLayer.show(); // 显示图层
        // mapvLayer.hide(); // 删除图层
	});
	</script>
</body>
</html>