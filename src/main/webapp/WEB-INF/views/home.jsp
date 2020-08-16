<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="include/header.jsp" />

<script>
	const msg = "${msg}";
	if(msg === "not-login") {
		alert("로그인이 필요한 서비스입니다.");
	}
</script>
<jsp:include page="include/footer.jsp" />
  
