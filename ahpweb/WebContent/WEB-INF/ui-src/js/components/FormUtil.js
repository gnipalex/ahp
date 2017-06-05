(function() {
  
    function createOrReplaceErrorForInput($input, errorMessage) {
        var inputContainer = $input.parent();
        var errorElement = inputContainer.find('.error');
        if (!errorElement.length) {
            errorElement = $('<div>').addClass('error text-danger');
            inputContainer.append(errorElement);
        }
        errorElement.text(errorMessage);
    }
    
    function resetErrors($form) {
        $form.find('.error').text('');
    }
    
    function displayErrors($form, errorMap) {
        resetErrors($form);
        _.each(errorMap, function(value, key) {
            var selectorByName = '[name=' + key + ']';
            var selector = ['input', 'select', 'textarea']
                .map(function(it) { return it + selectorByName; })
                .join(',');
            var $input = $form.find(selector);
            if ($input.length) {
                createOrReplaceErrorForInput($input, value);
            }
        });
    }
    
    app.components.FormUtil = {
        displayErrors : displayErrors,
        resetErrors : resetErrors
    };
    
})();