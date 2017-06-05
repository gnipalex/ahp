(function() {

    var $informationForm;
    
    function render() {
        $informationForm = $('#project-decision-info-form');
        $informationForm.submit(onUpdateBasicInformation);
        
        app.fragments.EditProjectDecisionAlternatives.render();
        app.fragments.EditProjectDecisionCriterias.render();
    }
    
    function onUpdateBasicInformation(e) {
        e.preventDefault();
        
        app.components.NotificationArea.clear();
        app.components.FormUtil.resetErrors($informationForm);
        
        var formData = $informationForm.serialize();
        $.post(window.location.href, formData)
            .done(function(response) {
                if (response.error) {
                    app.components.NotificationArea.showError('Basic information was not updated due to errors');
                    app.components.FormUtil.displayErrors($informationForm, response.fieldErrorMessages);
                } else {
                    app.components.NotificationArea.showSuccess('Information updated');
                }
            });
    }

    app.pages.EditProjectDecisionPage = {
        render : render
    };

})();