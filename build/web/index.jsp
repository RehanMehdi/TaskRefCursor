<%-- 
    Document   : index
    Created on : Mar 14, 2017, 12:34:13 PM
    Author     : Subhan-apollo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <title>Task Ref Cursor</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="getRefCur" method="GET">
            <table style="width: 100%;">
                <tr>
                    <th>Ticket ID</th>
                    <th>Date Time</th>
                    <th>Priority</th>
                    <th>WCID</th>
                    <th>Status</th>
                    <th>Repetition</th>
                    <th>Agent ID</th>
                    <th>SCID</th>
                    <th>Queue ID</th>
                    <th>Identity Num</th>
                </tr>
                <tr>
                    <td id="td1"></td>
                    <td id="td2"></td>
                    <td id="td3"></td>
                    <td id="td4"></td>
                    <td id="td5"></td>
                    <td id="td6"></td>
                    <td id="td7"></td>
                    <td id="td8"></td>
                    <td id="td9"></td>
                    <td id="td10"></td>
                </tr>
            </table>
        </form>

        <script>
            $(document).ready(function () {
                $.ajax({
                    type: "GET",
                    async: true,
//                    url: "aircom.htm",
                    data: {
                        action: "getRefCur"
                    },
                    success: function (data) {
                        $.getJSON(function (data) {
                            $("#td1").html(data.TICKETID);
                            $("#td2").html(data.DATETIME);
                            $("#td3").html(data.PRIORITY);
                            $("#td4").html(data.WCID);
                            $("#td5").html(data.STATUS);
                            $("#td6").html(data.REPETITION);
                            $("#td7").html(data.AGENTID);
                            $("#td8").html(data.SCID);
                            $("#td9").html(data.QUEUEID);
                            $("#td10").html(data.IDENTITYNUM);
                        });
                    }
                });
            });
        </script>

    </body>
</html>
