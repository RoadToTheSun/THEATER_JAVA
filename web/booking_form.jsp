<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="/tickets/">
    <div style="text-align: center">
        <p><label for="fio">
            <input type="text" id="fio" name="fio" placeholder="ФИО" required>
        </label></p>
        <p><label for="mail">
            <input type="text" id="mail" name="mail" placeholder="Почта">
        </label></p>
        <p><label for="phone">
            <input type="text" id="phone" name="tel" placeholder="Телефон" required>
        </label></p>
        <p>
            <button type="submit" name="booking-button">Сделать заказ</button>
        </p>
    </div>
</form>