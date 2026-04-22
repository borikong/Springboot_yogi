/**
 * 
 */
function submit() {
	document.submitForm.submit(); // Submits the form without the button
}

function handleLikeClick(selector) {

	$(selector).click(function () {
		const $this = $(this);
		const data = $(this).data();

		// 로그인 체크
		if (!data.c) {
			alert("로그인이 필요한 서비스입니다!");
			return;
		}

		// 상태 변경
		const isActive = data.b === 'active';
		const cmd = isActive?'dislike':'like'; //액티브하트->좋아요 취소
		$.ajax({
			url:'/member/like',
			type:'POST',
			data:{
				cmd:cmd,
				destId:data.a
			},
			success: function (res){
				$this.data("b", isActive ? "nonactive" : "active");

				const $img = $this.find("img");

				if (isActive) {
					$this.removeClass("active");
					$img.attr("src", "https://cdn-icons-png.flaticon.com/512/812/812327.png");
				} else {
					$this.addClass("active");
					$img.attr("src", "https://cdn-icons-png.flaticon.com/512/803/803087.png");
				}
			},
			error:function (){
				alert('처리 실패');
			}
		})
	});
}

// 실행
$(function () {
	handleLikeClick('.icon2.heart');
	handleLikeClick('.icon2.heart2');
});