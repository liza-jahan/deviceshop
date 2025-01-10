document.addEventListener('DOMContentLoaded', function () {
    const imageInput = document.getElementById('image');
    const imagePreview = document.getElementById('imagePreview');

    imageInput.addEventListener('change', function (event) {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imagePreview.src = e.target.result; // Set the preview image source
                imagePreview.style.display = 'block'; // Ensure the preview is visible
            };
            reader.readAsDataURL(file); // Convert the file to a data URL
        } else {
            imagePreview.src = '#'; // Reset the source
            imagePreview.style.display = 'none'; // Hide the preview
        }
    });
});
