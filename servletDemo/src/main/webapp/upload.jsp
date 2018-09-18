<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2018/9/18
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>
    <h2>上传文件实例</h2>
    <form  method="post" action="upload" enctype="multipart/form-data">
        <input type="file" name="uploadFile">
        <br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
