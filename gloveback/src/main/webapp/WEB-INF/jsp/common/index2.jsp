<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页用来测试</title>
</head>
<body>    	
	<div class="panel-body">
		<form id ="firstUpdateForm" action="demo/upload/singleFile" method="post"
	        enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">
	        <div>
	            <div>
		            <label>上传文件</label>
		            <div>
		                <input type="file"  name="files" />
		            </div>
	            </div>
	        </div>
	        <div>
	            <button type="submit" class="btn btn-default">确定 </button>
	        </div>
	    </form>
	 	<div>demo/upload/multiUploadTest</div>
	    <form  action="demo/upload/multiUploadTest" method="post" enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">
	        <div>
	            <div>
		            <label>上传文件</label>
		            <div>
		                <input type="file"  name="files" />
		                <input type="file"  name="files" />
		            </div>
	            </div>
	        </div>
	        <div>
	            <button  type="submit">确定 </button>
	        </div>
	    </form> 
        <div>这个是分割线，适用于multiUpload</div>
	   <form  action="demo/upload/multiUpload" method="post" enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">
	        <div>
	            <div>
		            <label>上传文件</label>
		            <div>
		                 <input type="file"  name="fileone" />
		                 <input type="file"  name="filetwo" />
		                 <input type="file"  name="filethree" />
		                 <input type="text" name="inputStr" />	               
		            </div>
	            </div>
	        </div>
	        <div>
	            <button  type="submit" >确定</button>
	        </div>
	    </form> 
	    <form  action="demo/upload/firstUpload" method="post" enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">
	       <div>
	            <div>
		            <label>上传文件</label>
		            <div>
		                <input type="file" id="firstDemoImgFile" name="files" />
		            </div>
	            </div>
	        </div>
	        <div>
	            <button id="createPeriadBtn" type="submit" class="btn btn-default">确定 </button>
	        </div>
       </form>
	</div>
</body> 
</html>