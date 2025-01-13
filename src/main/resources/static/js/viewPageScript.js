document.addEventListener('DOMContentLoaded', () => {
    const backButton = document.querySelector('.back-to-product');

    backButton.addEventListener('click', () => {
        alert('You are being redirected to the product page!');
    });
});
