function onEnter(inputID, buttonID) {
    var input = document.getElementById(inputID);

    // Execute a function when the user releases a key on the keyboard
    input.addEventListener("keyup", function(event) {
        // Cancel the default action, if needed
        event.preventDefault();
        
        // Number 13 is the "Enter" key on the keyboard
        if (event.keyCode === 13) {
            // Trigger the button element with a click
            document.getElementById(buttonID).click();
        }
    });
}