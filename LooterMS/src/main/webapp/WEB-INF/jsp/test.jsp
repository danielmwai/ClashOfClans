<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test</title>
        <link rel="stylesheet" type="text/css"
              href="<%=request.getContextPath()%>/extjs/resources/css/ext-all.css"/>
        <style type="text/css">
            html, body {
                font: normal 12px verdana;
                margin: 0;
                padding: 0;
                border: 0 none;
                overflow: hidden;
                height: 100%;
            }

            .x-panel-body p {
                margin: 5px;
            }

            .x-column-layout-ct .x-panel {
                margin-bottom: 5px;
            }

            .x-column-layout-ct .x-panel-dd-spacer {
                margin-bottom: 5px;
            }
        </style>
        <script type="text/javascript"
        src="<%=request.getContextPath()%>/extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-all.js"></script>

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/test.js"></script>
    </head>
    <body>
        <div id="div1"></div>
    </body>
</html>