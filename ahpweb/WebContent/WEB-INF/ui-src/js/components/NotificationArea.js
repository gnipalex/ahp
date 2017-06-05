(function() {
  
    var $headerMessages = $('#header-messages');
    
    function showError(message) {
        var div = $('<div class="alert alert-danger" role="alert">');
        div.text(message);
        $headerMessages.append(div);
    }
    
    function showSuccess(message) {
        var div = $('<div class="alert alert-success" role="alert">');
        div.text(message);
        $headerMessages.append(div);
    }
    
    function clear() {
        $headerMessages.empty();
    }
    
    app.components.NotificationArea = {
        showError : showError,
        showSuccess : showSuccess,
        clear : clear
    }
    
})();