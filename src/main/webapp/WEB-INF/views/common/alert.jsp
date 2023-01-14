<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script>
    let msg = "<c:out value='${msg}'/>";
    let url = "<c:out value='${url}'/>";
    alert(msg);
    location.href = url;
</script>