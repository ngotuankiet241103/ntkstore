<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="add" method="post" class="formPost"
		enctype="multipart/form-data">
		<div class="form-post">


			<div class="box-form">
				<div class="blog">
					<input type="text" placeholder="Tiêu đề " name="title" />
				</div>
				<div class="blog">
					<textarea name="" id="content" cols="30" rows="10"
						placeholder="Nội dung bài viết"></textarea>
					<input name="content" type="hidden">
				</div>
				<input type="hidden" name="description" class="description">
			</div>
			<div class="box-category-post">
				<div class=" blog">
					<label for="">Chủ đề</label>
					<div class="form-input-radio">
						<c:forEach items="${topics}" var="topic">
							<div class="radio-group">
								<input type="radio" id="${topic.name}" name="topicName" value="${topic.name}" /> <label for="name">${topic.name}</label>
							</div>
						</c:forEach>
						<!-- <div class="radio-group">
							<input type="radio" id="name" name="topicName"  value="clothes"/> <label for="name">clothes</label>
						</div>
						<div class="radio-group">
							<input type="radio" id="topic" name="topicName" /> <label
								for="topic">name</label>
						</div> -->
						<div class="radio-group other">
							<input type="radio" id="other-radio" name="topicName" /> <label
								for="other-radio">other</label> <input type="text"
								class="topicOther" />
						</div>
					</div>
				</div>
				<div class="blog">
					<label for="">Tag</label> <input type="text"
						placeholder="VD: clothes" class="input-tag" name="tagName" />
				</div>
				<div class="blog">
					<label for="">Ảnh đại diện</label> <input type="file"
						name="fileImage">
				</div>
				<div>
					<button type="button" class="addBlog">Xuất bản</button>
				</div>
			</div>

		</div>
	</form>
	<link href="<c:url value= '/template/admin/css/ckeditorBlog.css' />"
		rel="stylesheet">
	<script
		src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>

	<script>
		
      const editor = ClassicEditor.create(
        document.querySelector("#content"),
        {}
      ).catch((error) => {
        console.error(error);
      });

      editor
        .then((editor) => {
        	 
     	   
          document.querySelector(".addBlog").onclick = () => {
        	  const text = editor.getData();
        	  const lines = text.split("\n");

        	  
              document.querySelector('input[name="content"]').value = text;
        	  for(let i = 0; i < lines.length ; i++){
        		  const str = lines[i];
        		  if(str.indexOf("<p>") >= 0){
          			  const newStr = str.substring(str.indexOf("<p>") + 3,str.indexOf("</p>"))
          			  console.log(newStr)
          			  document.querySelector('.description').value = newStr;
          			  break;
          		  }
         		}
        	  document.querySelector(".formPost").submit();  
        	    
          };
        })
      .catch((error) => {
          // Handle the error
        });
     
      const radioOther = document.querySelectorAll(
        '.radio-group input[type="radio"]'
      );
      radioOther.forEach((input) => {
        input.onclick = () => {
          const text = document.querySelector(".other .topicOther");
          if (input.id == "other-radio") {
            text.classList.add("active");
            text.onchange = () => {
            	const parent = text.parentElement;
            	const element = parent.querySelector('#other-radio')
            	
            	element.value = text.value;
            };
          } else {
            text.classList.remove("active");
          }
        };
      });
    </script>
</body>
</html>