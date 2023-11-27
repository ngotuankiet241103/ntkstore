  
function commentBlog(api) {
	fetch(api)
		.then(response => response.json())
		.then(object => {
			const userId = document.querySelector('input[name="userId"]').value
			const showModalComment = document.querySelector('.showModalComment')
			const modalComment = document.querySelector('.modal-comment')
			showModalComment.onclick = () => {
				modalComment.classList.add("active")
				handleBoxComment(object, userId)
				const closeModelComment = document.querySelector('.header-close > i')
				closeModelComment.onclick = () => modalComment.classList.remove("active")
			}
		})


}
function handleCommentRoot() {
	const editor = ClassicEditor.create(
		document.querySelector(".root"),
		{}
	).catch((error) => {
		console.error(error);
	});

	editor.then((editor) => {
		const sendComment = document.querySelector(".send-comment");
		console.log(sendComment);
		const cancelComment = document.querySelector(".cancel-comment");
		console.log(cancelComment);
		cancelComment.onclick = () => {
			const parentFormComment = isParentElement(
				cancelComment,
				"rootComment"
			);
			parentFormComment.innerHTML = `<input
                          type="text"
                          class="inputComment"
                          placeholder="Viết bình luận của bạn"
                        />`;
			focusComment()
		};
		sendComment.onclick = () => {
			const blogId = document.querySelector('input[name="blogId"]').value;
			const userId = document.querySelector('input[name="userId"]').value;
			const data = editor.getData();
			const commentData = {
				blogId,
				userId,
				content: data,
				tree_id: null,
				parent_id: null,
			};
			console.log(commentData)
			postComment(commentData)
				.then(response => {
					if (response.status === 200) {
						location.reload();
					}
				})
		};
	});
}
function isParent(element, exits) {
	while (!element.className.includes(exits)) {
		element = element.parentElement;
	}
	return element.parentElement;
}
function isParentElement(element, exits) {
	while (!element.className.includes(exits)) {
		element = element.parentElement;
	}
	return element;
}
function focusComment() {
	const inputComment = document.querySelector(".inputComment");
	if (inputComment != null) {
		inputComment.onfocus = () => {
			const inputCommentParent = inputComment.parentElement;
			inputCommentParent.innerHTML = `<div class ="form-reply">
          <div class="comment-reply">
                <textarea name="" id="reply" cols="30" rows="10" class="root">
                </textarea>
                <div class="oparation-form-reply">
                  <button class="cancel-comment">Hủy</button>
                  <button class="send-comment" >Trả lời</button>
                </div>
              </div>
          </div>`;
			handleCommentRoot();
		};
	}

}
function postComment(data) {
	var option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	};
	return fetch("http://localhost:8080/e-commerceSpringMvc/api/blog/comment", option);
}

function handleBoxComment(object, userId) {
	const commentBlog = document.querySelector(".comments");
	const compareFunction = (a, b) => b.left - a.left;
	let html = "";
	const commentRoot = document.querySelector(".rootComment");

	if (userId != "") {
		let textCommentRoot = ` 
	      <input
	        type="text"
	        class="inputComment"
	        placeholder="Nhập suy nghĩ của bạn"
	      />`;
		commentRoot.innerHTML = textCommentRoot;
	}
	for (const [key, value] of Object.entries(object)) {
		i = 0;

		value.forEach((cmt, index, arr) => {
			let depth = 0;
			value.forEach((subCmt) => {
				if (subCmt.node_left < cmt.node_left && subCmt.node_right > cmt.node_right) {
					depth++;
				}
			});

			if (depth == 0) {
				html += `
            <li class="comment-item">
              <div class="comment-root" data-index="${cmt.tree_id}" data-id="${cmt.id}">
                <div class="comment-drx2">
                  <div class="box-authorComment">
                    <div class="nameUser-comment">${cmt.user.fullName}</div>
                    <div class="content-comment">${cmt.comments}</div>
                  </div>`;
				if (userId != null) {
					html += ` <div class="comment_time">
                    <button><span>Thích</span></button>
                    <button class="btn-comment"><span>Bình luận</span></button>
                  </div>`;
				}
				if (arr.length - 1 > depth) {
					html += `  </div>
                      </div>
		              <div class="viewComment-reply">
		                <div class="show">
		                  <span>Xem ${arr.length - 1} câu trả lời</span>
		                  <span><i class="fa-solid fa-chevron-down"></i></span>
		                </div>
		                <div class="hidden ">
		                  <span>Ẩn ${arr.length - 1} câu trả lời</span>
		                <span><i class="fa-solid fa-chevron-down"></i></span>
		                </div>
		              </div>

              			`;
				}

			} else {
				html += ` <div class="child-comment" data-index="${cmt.tree_id}" data-id="${cmt.id}">
                <div class="comment-drx2">
                  <div class="box-authorComment">
                    <div class="nameUser-comment">Người dùng 1</div>
                    <div class="content-comment">${cmt.comments}</div>
                  </div>`;
				if (userId != null) {
					html += `<div class="comment_time">
                    <button><span>Thích</span></button>
                    <button class="btn-comment"><span>Bình luận</span></button>
                  </div>`;
				}
				html += `  </div>
              </div>`;
			}
		});
		html += `</li>`;
	}
	commentBlog.innerHTML = html;
	focusComment();
	const parentCommentReply = document.querySelectorAll('.viewComment-reply');
	if (parentCommentReply.length > 0) {
		parentCommentReply.forEach(reply => {
			const showCommentReply = reply.querySelectorAll(
				"div"
			);
			if (showCommentReply.length > 0) {
				showCommentReply.forEach((view) => {
					if (userId != null) {
						const comments = document.querySelectorAll(".btn-comment");
						comments.forEach((button) => {
							button.onclick = () => {
								const buttonActive = document.querySelector(
									".btn-comment.active"
								);
								if (buttonActive != null) {
									return;
								}
								const replyComment = isParent(button, "comment-drx2");
								console.log(replyComment);
								let formPost = replyComment.querySelector(".form-reply");
								const parentId = replyComment.getAttribute("data-id");
								const treeId = replyComment.getAttribute("data-index");
								if (formPost == null) {
									const myStrong = document.createElement("div");
									myStrong.className = "form-reply";
									myStrong.innerHTML = `<div class="comment-reply">
			                <textarea name="" id="reply" cols="30" rows="10" class="reply">
			                </textarea>
			                <div class="oparation-form-reply">
			                  <button class="cancel-reply">Hủy</button>
			                  <button class="send-reply" >Trả lời</button>
			                </div>
			              </div>`;

									replyComment.appendChild(myStrong);
								}

								button.classList.add("active");

								editForm(treeId, parentId);
							};
						});
					}

					view.onclick = () => {
						showCommentReply.forEach((e) => e.classList.toggle("active"));
						const boxComment = isParentElement(view, "comment-item");
						console.log(boxComment);
						boxComment.classList.toggle("active");
					};
				});
			}
		})

	}


	else {
		if (userId != null) {
			const comments = document.querySelectorAll(".btn-comment");
			comments.forEach((button) => {
				button.onclick = () => {
					const buttonActive = document.querySelector(
						".btn-comment.active"
					);
					if (buttonActive != null) {
						return;
					}
					const replyComment = isParent(button, "comment-drx2");
					console.log(replyComment);
					let formPost = replyComment.querySelector(".form-reply");
					const parentId = replyComment.getAttribute("data-id");
					const treeId = replyComment.getAttribute("data-index");
					if (formPost == null) {
						const myStrong = document.createElement("div");
						myStrong.className = "form-reply";
						myStrong.innerHTML = `<div class="comment-reply">
                <textarea name="" id="reply" cols="30" rows="10" class="reply">
                </textarea>
                <div class="oparation-form-reply">
                  <button class="cancel-reply">Hủy</button>
                  <button class="send-reply" >Trả lời</button>
                </div>
              </div>`;

						replyComment.appendChild(myStrong);
					}

					button.classList.add("active");

					editForm(treeId, parentId);
				};
			});
		}
	}



	function editForm(tree_id, parent_id) {
		const editor = ClassicEditor.create(
			document.querySelector(".reply"),
			{}
		).catch((error) => {
			console.error(error);
		});

		editor
			.then((editor) => {
				const cancelReply = document.querySelector(".cancel-reply");

				const sendReply = document.querySelector(".send-reply");

				console.log(sendReply);
				console.log(editor);
				const user = "Messi";
				editor.config.allowedContent = true;
				editor.setData(
					`<p class="userReply" style="color: red;">@${user}</p>`
				);
				// document.querySelector(".addProduct").onclick = () => {
				//   document.querySelector('input[name="description"]').value =
				//     editor.getData();
				//   document.querySelector(".formProduct").submit();
				// };
				// sendReply.onclick = () =>  console.log("true")
				cancelReply.onclick = (e) => {
					const formReply = isParent(e.target, "form-reply");
					formReply.remove();
				}
				sendReply.onclick = () => {
					console.log("a");
					const data = editor.getData();
					let newData =
						`<span class="userReply">` +
						data.substring(
							data.indexOf("@") + 1,
							data.indexOf(`${user}`) + user.length
						)  +
						"<spann>" +
						data.substring(data.indexOf(`${user}`) + user.length, data.length) + "</" + "span>" +
						"</" +
						"span>";
					const blogId = document.querySelector('input[name="blogId"]').value;
					const commentData = {
						blogId,
						userId,
						content: newData,
						tree_id,
						parent_id,
					};
					console.log(commentData);
					const postCommentf = postComment(commentData);

					postCommentf.then((response) => {
						if (response.status === 200) {
							console.log("success")
							const wrapperComment = isParentElement(
								sendReply,
								"comment-item"
							);
							console.log(newData);
							const commentReply = document.createElement("div");
							commentReply.className = "child-comment";
							commentReply.innerHTML = `
				                <div class="comment-drx2">
				                  <div class="box-authorComment">
				                    <div class="nameUser-comment">Người dùng 1</div>
				                    <div class="content-comment">${newData}</div>
				                  </div>
				                  <div class="comment_time">
				                    <button><span>Thích</span></button>
				                    <button class="btn-comment"><span>Bình luận</span></button>
				                  </div>
				                </div>
				              `;
							const formReply = isParent(sendReply, "form-reply");
							console.log(formReply);
							wrapperComment.appendChild(commentReply);
							formReply.remove();
						}
					});


				};
			})

			.catch((error) => {
				// Handle the error
			});
	}
}