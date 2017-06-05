(function() {

    var $informationForm;
    
    function render() {
        $informationForm = $('#project-decision-info-form');
        $informationForm.submit(onUpdateBasicInformation);
        
        $('.js-add-alternative').click(onAddAlternativeClick);
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
                    app.components.FormUtil.displayErrors($informationForm, response.errorMessages);
                } else {
                    app.components.NotificationArea.showSuccess('Information updated');
                }
            }).fail(function() {
                alert('update project decision basic information failed');
            });
    }
    
    function onAddAlternativeClick() {
        var modal = app.templates['templates/popup/createAlternative']({
            title : 'Create Alternative'
        });
        var $modal = $(modal);
        $modal.find('#create-alternative-save-btn').click(createAlternative);
        $modal.on('hidden.bs.modal', function() {
            $modal.remove();
        });
        $modal.modal();
    }

    function createAlternative(e) {
        alert('Create alternative');
    }

    app.pages.EditProjectDecisionPage = {
        render : render
    };

})();