function showAlert() {
    alert("The button was clicked!");
}

document.addEventListener("DOMContentLoaded", function() {

    const paymentForm = document.getElementById("paymentForm");
    const paymentId = paymentForm.getAttribute("data-payment-id");
    const merchantId = paymentForm.getAttribute("data-merchant-id");

    const submitButton = document.getElementById("submitPaymentButton");
    if (submitButton) {
        submitButton.addEventListener("click", function() {
            submitPayment(paymentId, merchantId);
        });
    }
});

function submitPayment(paymentId, merchantId) {

    const form = document.getElementById("paymentForm");
    const formData = new FormData(form);

    const data = {
        paymentId: paymentId,
        merchantId: merchantId,
        cardNumber: formData.get("cardNumber"),
        cardHolderName: formData.get("cardHolderName"),
        expirationMonth: formData.get("expirationMonth"),
        expirationYear: formData.get("expirationYear"),
        cardSecurityCode: formData.get("cvv")
    };

    fetch('http://localhost:8082/api/payment/initiate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            window.location.replace(data.redirectUrl)
        })
        .catch(error => {
            console.error("Error:", error);
        });
}
