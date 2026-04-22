function searchIdPrompt() {
    const url = "/searchId"; // 보통 컨트롤러 매핑 경로로
    window.open(url, "searchIdPopup", "width=450,height=500,left=550,top=350");
}

function searchPwPrompt() {
	url = "/searchPass";
	window.open(url, "searchPassPopup", "width=450,height=500,left=550,top=350");
}


function searchId() {	//아이디 찾기
	if (document.idproc.name.value == "") {
		alert("이름을 입력해주세요.");
		document.idproc.name.focus();
		return false;
	}

	if (document.idproc.email.value == "") {
		alert("이메일을 입력해주세요.");
		document.idproc.email.focus();
		return false;
	}

	document.idproc.submit();
}

function searchPass() {	//비밀번호 찾기
	if (document.passproc.name.value == "") {
		alert("이름을 입력해주세요.");
		document.passproc.name.focus();
		return false;
	}

	if (document.passproc.email.value == "") {
		alert("이메일을 입력해주세요.");
		document.passproc.email.focus();
		return false;
	}

	if (document.passproc.id.value == "") {
		alert("아이디를 입력해주세요.");
		document.passproc.id.focus();
		return false;
	}

	document.passproc.submit();
}


function checkclose_id() { //아이디 중복확인 창 사용 가능한 아이디가 아니면
	var id = document.getElementById("idinput").value;
	var email = document.getElementById("emailinput").value
	var check = document.getElementById("checkinput").value
	var check2 = document.getElementById("check2input").value
	opener.location.href = "mem.me?cmd=regForm&checkid=" + "&checkemail=" + email + "&check=0" + "&check2=" + check2;
	window.close();
}

function close_id() { //아이디 사용 가능함
	var id = document.getElementById("idinput").value;
	var email = document.getElementById("emailinput").value
	var check2 = document.getElementById("check2input").value
	opener.location.href = "mem.me?cmd=regForm&checkid=" + id + "&checkemail=" + email + "&check=1" + "&check2=" + check2;
	window.close();
}

function close_email() { //이메일 사용 가능함
	var id = document.getElementById("idinput").value;
	var email = document.getElementById("emailinput").value
	var check = document.getElementById("checkinput").value
	opener.location.href = "mem.me?cmd=regForm&checkid=" + id + "&checkemail=" + email + "&check2=1" + "&check=" + check;
	window.close();
}

function checkclose_email() { //이메일 중복확인 창 닫기, 이메일 사용 불가
	var id = document.getElementById("idinput").value;
	var email = document.getElementById("emailinput").value
	var check = document.getElementById("checkinput").value
	opener.location.href = "mem.me?cmd=regForm&checkid=" + id + "&checkemail=" + "" + "&check2=0" + "&check=" + check;
	window.close();
}



function close_email_modify() { //이메일 사용 가능함
	var email = document.getElementById("emailinput").value
	opener.location.href = "mem.me?cmd=modifyForm&checkemail=" + email + "&check2=1";
	window.close();
}

function checkclose_email_modify() { //이메일 중복확인 창 닫기, 이메일 사용 불가
	var email = document.getElementById("emailinput").value
	opener.location.href = "mem.me?cmd=modifyForm&checkemail=" + "" + "&check2=0";
	window.close();
}

////id 중복확인
//function idCheck(id, email) {
//	if (id == "") {
//		alert("아이디를 입력해주세요");
//		document.regForm.id.focus();
//	} else {
//		url = "member.me?cmd=idCheck&id=" + id + "&email=" + email + "&check2=1";
//		window.open(url, "post", "width=500, height=300,left=550, top=350");
//	}
//}
function idCheck(id) {
    if (!id) {
        alert("아이디를 입력하세요.");
        return;
    }

    fetch(`/member/check-id?id=${encodeURIComponent(id)}`)
        .then(response => response.json())
        .then(data => {
            const msgBox = document.getElementById("idCheckMsg");

            if (data.result) {
                msgBox.innerHTML = "<span style='color:green;'>사용 가능한 아이디입니다.</span>";
                document.getElementById("idCheckFlg").value='1';
            } else {
                msgBox.innerHTML = "<span style='color:red;'>이미 사용중인 아이디입니다.</span>";
                document.getElementById("idCheckFlg").value='0';
            }
        })
        .catch(error => {
            console.error("error:", error);
            alert("중복확인 실패");
        });
}


function emailCheck(email) {
    if (!email) {
        alert("이메일을 입력하세요.");
        return;
    }

    fetch(`/member/check-email?email=${encodeURIComponent(email)}`)
        .then(response => response.json())
        .then(data => {
            const msgBox = document.getElementById("emailCheckMsg");

            if (data.result) {
                msgBox.innerHTML = "<span style='color:green;'>사용 가능한 이메일입니다.</span>";
                document.getElementById("emailCheckFlg").value='1';
            } else {
                msgBox.innerHTML = "<span style='color:red;'>이미 사용중인 이메일입니다.</span>";
                document.getElementById("emailCheckFlg").value='0';
            }
        })
        .catch(error => {
            console.error("error:", error);
            alert("중복확인 실패");
        });
}

//modifyForm에서 email 중복 확인
function emailCheck_modify(email) {
	if (email == "") {
		alert("이메일을 입력해주세요");
		document.regForm.email.focus();
	} else {
		url = "member.me?cmd=emailCheck&modify=1" + "&email=" + email;
		window.open(url, "post", "width=500, height=300,left=550, top=350");
	}
}


function zipCheck() {
	url = "member.me?cmd=zipCheck&check=y";
	window.open(url, "post", "toolbar=no, width=500,left=550, top=350, height=300, directories=no, status=yes, scrollbars=yes, menubar=no");
}

function dongCheck() {
	if (document.zipForm.dong.value == "") {
		alert("동 이름을 입력하세요.");
		document.zipForm.dong.focus();
		return;
	}
	document.zipForm.submit();
}

function sendAddress(zipcode, sido, gugun, dong, ri, bunji) {
	var address = sido + " " + gugun + " " + dong + " " + ri + " " + bunji;
	opener.document.regForm.zipcode.value = zipcode;
	opener.document.regForm.address1.value = address;
	self.close();
}

function inputCheck() {

	if (document.regForm.id.value == "") {
		alert("아이디를 입력해주세요.");
		document.regForm.id.focus();
		return;
	}

	if (document.regForm.pass.value == "") {
		alert("비밀번호를 입력해주세요.");
		document.regForm.pass.focus();
		return;
	}

	if (document.regForm.repass.value == "") {
		alert("비밀번호를 확인해주세요");
		document.regForm.repass.focus();
		return;
	}

	if (document.regForm.pass.value != document.regForm.repass.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.repass.focus();
		return;
	}

	if (document.regForm.name.value == "") {
		alert("이름을 입력해주세요.");
		document.regForm.name.focus();
		return;
	}

	if (document.regForm.email.value == "") {
		alert("이메일을 입력해주세요.");
		document.regForm.email.focus();
		return;
	}

	if (document.regForm.zipcode.value == "") {
		alert("우편번호를 입력해주세요.");
		document.regForm.zipcode.focus();
		return;
	}

	if (document.regForm.address1.value == "") {
		alert("주소를 입력해주세요.");
		return;
	}

	if(document.regForm.idCheckFlg.value!="1"){
	    alert("아이디 중복확인이 필요합니다.");
    	return;
	}

    if(document.regForm.emailCheckFlg.value!="1"){
        alert("이메일 중복확인이 필요합니다.");
        return;
    }

	document.regForm.submit();
}

function updateCheck() {

	if (document.regForm.pass.value == "") {
		alert("비밀번호를 입력해주세요.");
		document.regForm.pass.focus();
		return;
	}

	if (document.regForm.repass.value == "") {
		alert("비밀번호를 확인해주세요");
		document.regForm.repass.focus();
		return;
	}

	if (document.regForm.pass.value != document.regForm.repass.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.repass.focus();
		return;
	}

	if (document.regForm.email.value == "") {
		alert("이메일을 입력해주세요.");
		document.regForm.email.focus();
		return;
	}

	if (document.regForm.zipcode.value == "") {
		alert("우편번호를 입력해주세요.");
		document.regForm.zipcode.focus();
		return;
	}

	if (document.regForm.address1.value == "") {
		alert("주소를 입력해주세요.");
		return;
	}

	document.regForm.submit();
}



