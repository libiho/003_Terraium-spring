<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>





 <div class="content">
        <br><br>
        <div class="innerOuter" style="padding:5% 10%;">
            <h2>게시판</h2>
            <br>
            
	            <!-- 로그인후 상태일 경우만 보여지는 글쓰기 버튼-->
	            <c:if test="${not empty loginUser}">
           			<a class="btn btn-secondary btn-sm" style="float:right" href="enrollForm.bo">글쓰기</a>
           		</c:if>
           		
            <br><br>
            
            
            <table id="boardList" class="table table-hover" align="center">
               <thead>
                   <tr>
                     <th>글번호</th>
                     <th>제목</th>
                     <th>작성자</th>
                     <th>조회수</th>
                     <th>작성일</th>
                     <th>첨부파일</th>
                   </tr>
                 </thead>
                <tbody>
                
                
                	<c:forEach var="b" items="${list }">
                
                
	                    <tr>
	                        <td class="bno">${b.boardNo }</td>
	                        <td>${b.boardTitle }</td>
	                        <td>${b.boardWriter }</td>
	                        <td>${b.count }</td>
	                        <td>${b.createDate }</td>
	                        <td>
	                        	<c:if test="${not empty b.originName }">
	                        		★
	                        	</c:if>
	                        </td>
	                    </tr>

                    </c:forEach> 
                    <!-- class="bno"는 무조건!!!!!!!! class 왜?? 반복문이라서 class는 가능한데 id로 하게되면 id 중복되므로 class -->
                    
                </tbody>
            </table>
            <br>
		
		
		<script>
			$(function() {
				$("#boardList>tbody>tr").click(function() {
					location.href="detail.bo?bno="+ $(this).children(".bno").text();
												// 선택당한 tr에 자식중에 클래스가 bno인 것의 text를 가져오기
													
				})
			})
		
		</script>
		
			
            <div id="pagingArea">
                <ul class="pagination">
                
                		<c:choose>
                		
                			<c:when test="${pi.currentPage eq 1 }">
	                   			 <li class="page-item disabled"><a class="page-link" href="">Previous</a></li>
	                    	</c:when>
	                    		
	                    	<c:otherwise>
	                    		<li class="page-item "><a class="page-link" href="list.bo?cpage=${pi.currentPage - 1 }">Previous</a></li>
	                    	</c:otherwise>
	                    
	                    </c:choose>
	                    <!-- 1페이지면 이전을 disabled해서 막고 아닐때는 작동하게 그리고 disabled라서 href는 막음 -->
	                    
	                    
	                    <c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
                    		<li class="page-item"><a class="page-link" href="list.bo?cpage=${ p }"> ${ p } </a></li>
                    	</c:forEach>
                    	
                    	<c:choose>
                    		<c:when test="${pi.currentPage eq pi.maxPage }">
	                  			 <li class="page-item disabled"><a class="page-link" href="">Next</a></li>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<li class="page-item"><a class="page-link" href="list.bo?cpage=${pi.currentPage + 1 }">Next</a></li>
	                   		 </c:otherwise>
	                    
	                    </c:choose>
                </ul>
            </div>
           
            <br clear="both"><br>
            

            <form id="searchForm" action="" method="get" align="center">
                <div class="select">
                    <select class="custom-select" name="condition">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword">
                </div>
                <button type="submit" class="searchBtn btn btn-secondary">검색</button>
            </form>
            <br><br>
        </div>
        <br><br>
    </div>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

</body>
</html>