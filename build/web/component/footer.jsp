<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<footer>
    <div class="container-fluid">
        <div class="row" id="footer_top">
            <div>
                <a href="">Giới thiệu</a>
                <a href="">Hỏi & đáp</a>
                <a href="">Chính sách bảo mật</a>
            </div>
            <div class="right">
                Kết nối cùng chúng tôi
                <i class="fa-brands fa-facebook"></i>
                <i class="fa-brands fa-youtube"></i>
                <i class="fa-brands fa-twitter"></i>
            </div>
        </div>
        <div class="row" id="footer_bot">
            <div class="col-md-6">
                <h1>ĐỊA CHỈ VÀ THÔNG TIN LIÊN HỆ</h1>
                <div class="row" style="padding: 10px;background-color: aliceblue;">
                    <div class="col-md-6">
                        <p>Đại học fpt, Thạch Thất, Hà Nội</p>
                        <p><b>MST:</b> 0123654987</p>
                    </div>
                    <div class="col-md-6">
                        <p><b>TEL:</b> (028)22222364</p>
                        <p> <b>HotLine:</b>1900 343 463 </p>
                        <p><b>Email:</b> chamsockhachhang@gmail.com</p>
                    </div>

                </div>
            </div>

            <div class="col-md-6">
                <h1>Liên hệ cần hỗ trợ hoặc nhận thông tin</h1>
                <div>
                    <form method="post" action="AddnewContact">
                        <div class="row" >
                            <div  class="col-md-6" >
                                <input placeholder="Họ Tên(*)" class="form-control input-contact" name="name"  value="${sessionScope.user.fullname}">
                            </div>
                            <div class="col-md-6 ">
                                <select class="form-control " name="webcontact">
                                    <c:forEach var="s" items="${slist}">
                                        <option value="${s.type_id}">${s.setting_title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6 ">
                                <input placeholder="Email(*)" class="form-control input-contact"  name="mail" value="${sessionScope.user.mail}">

                            </div>
                            <div class="col-md-6 ">
                                <input placeholder="Số điện thoại(*)" class="form-control input-contact" name="phone"  value="${sessionScope.user.mobie}">
                            </div>
                            <div class="col-md-12 " >
                                <textarea class="form-control input-contact " name="mess">

                                </textarea>
                            </div>
                            <button type="submit" class="btn btn-primary" style="margin-left: 25px;" >Gửi thông tin</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</footer>