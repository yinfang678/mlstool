<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid.css">
<link rel="stylesheet" type="text/css" href="static/css/ui-grid.css" />
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<script src="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid.debug.js"></script>
<script
	src="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid-flexible-height.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.14.3/ui-bootstrap-tpls.min.js"></script>
<script type="text/javascript" src="static/js/ui-grid.js"></script>
<script type="text/javascript" src="static/js/func.js"></script>
<script type="text/javascript" src="static/js/webApp.js"></script>
<script type="text/javascript" src="static/js/grid.js"></script>

<title>MLS TOOL</title>
<style type="text/css">
h4 {
	margin-top: 25px;
}

.row {
	margin-bottom: 20px;
}

.row .row {
	margin-top: 10px;
	margin-bottom: 0;
}

hr {
	margin-top: 40px;
	margin-bottom: 40px;
}

.title {
	text-align: center;
}

.ngViewport {
	width: auto !important;
	overflow-x: scroll;
}

.panel-body {
	padding: 0px;
}

a {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
</style>
</head>
<body ng-app="myApp">
	<div id="mainDiv" class="container-full" ng-controller="webController">
		<%--<div class="page-header">
			<h1 class="title">房源解析开发环境</h1>
			<p class="lead">副标题</p>
		</div>--%>
		<div class="row" style="margin-top: -10px;">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<div class="panel-heading"
					style="height: 15px; display: table-cell;">
					Resources: <select ng-model="selectedResource">
						<option ng-repeat="x in resources">{{x}}</option>
					</select>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel-heading"
					style="height: 15px; display: table-cell;">
					CLASS: <select ng-model="selectedClass">
						<option ng-repeat="x in classes">{{x}}</option>
					</select>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel-heading"
					style="height: 15px; display: table-cell;">
					Sample: <select ng-model="selectedSample">
						<option ng-repeat="x in samples">{{x}}</option>
					</select>
				</div>
			</div>
		</div>
		<%-- 字段比对列表 --%>
		<div class="row">
			<div id="leftGridDiv" class="col-md-6" ng-controller="customersCtrl">
				<div class="panel panel-default">
					<div class="panel-heading title">listing_info_full表字段</div>
					<div class="panel-body " ui-grid="gridOptions" ui-grid-selection
						ui-grid-edit ui-grid-resize-columns style="height: 480px;"></div>
				</div>
			</div>
			<div class="col-md-1" style="text-align: center; margin-top: 100px">
				<div class="btn-group btn-group-vertical" role="group"
					aria-label="...">
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-info" ng-click="update()">编辑</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-primary" ng-click="addElem()">设置</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-warning" ng-click="repeal()">清除</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-success"
							ng-click="saveFile()">保存</button>
					</div>
					<div style="height:100px" class="btn-group" role="group">
						
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-warning" 
							ng-click="download()">采样</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default"
							ng-click="saveData()">复制</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default"
							ng-click="copyData()">粘贴</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-danger" ng-click="reset()">重置</button>
					</div>
				</div>
			</div>
			<div id="mlsGridDiv" class="col-md-5" ng-controller="customersCtrl2">
				<div class="panel panel-default">
					<div class="panel-heading title">MLS字段</div>
					<div class="panel-body" ui-grid="mlsGrid" style="height: 480px"
						ui-grid-selection ui-grid-resize-columns></div>
				</div>
			</div>
		</div>

		<script type="text/ng-template" id="myModalContent.html">
            <div class="modal-header">
                <h3 class="modal-title title">JS 编辑页面</h3>
            </div>
            <div class="modal-body">

                <div class="panel panel-default">
                    <div class="panel-heading title">JS 输入框</div>
                    <div class="panel-body ">
                        <textarea ng-model="item" rows="15" cols="68">{{ item }}</textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" ng-click="ok()">OK</button>
                <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
            </div>
        </script>

	</div>
	<script>
    var app = angular.module('myApp', [ 'ngGrid', 'ui.bootstrap', 'ui.grid',
        'ui.grid.selection', 'ui.grid.edit', 'ui.grid.resizeColumns',
        'ui.grid.autoResize' ]);
    app.value("selectedItems", []);
    var lcolStr = '${listing_cols}';
    var mls_meta = JSON.parse('${mls_meta}');
    var lcolJSON = JSON.parse(lcolStr);
    var mlsId = '${mlsId}';
    var sampleData = null;
    initWebApp(app);
    init(app);
  </script>
</body>
</html>