(function() {

    var projectId,
        projectDecisionId;

    function render() {
        projectId = $('#edit-project-decision-data-element').data('project-id');
        projectDecisionId = $('#edit-project-decision-data-element').data('decision-id');
        
        $('.js-add-alternative').click(onAddAlternativeClick);
        
        setAlternativeListHandlers();
    }
    
    function setAlternativeListHandlers() {
        var $alternativeItems = $('.js-alternative-list .js-alternative');
        
        $alternativeItems.find('.js-edit-alternative').click(function() {
            var $alternativeItem = $(this).closest('.js-alternative');
 
            var $modal = $(app.templates['templates/popup/createAlternative']({
                title : 'Update alternative',
                name : $alternativeItem.data('name'),
                description : $alternativeItem.data('description'),
                id : $alternativeItem.data('id')
            }));

            $modal.on('hidden.bs.modal', function() {
                $modal.remove();
            });
            
            $modal.find('#create-alternative-save-btn').click(updateAlternative);
            
            $modal.modal();
        });
        
        $alternativeItems.find('.js-remove-alternative').click(function() {
            var $alternativeItem = $(this).closest('.js-alternative');
            var alternativeId = $alternativeItem.data('id');
            $.post(getUrlPrefix() + '/alternative/' + alternativeId + '/delete')
                .done(function(response) {
                    app.components.NotificationArea.clear();
                    if (response.error) {
                        app.components.NotificationArea.showError('Alternative was not removed');
                    } else {
                        app.components.NotificationArea.showSuccess('Alternative was removed');
                        if ($alternativeItem.siblings().length) {
                            $alternativeItem.remove();
                        } else {
                            refreshAlternativesList(null);
                        }
                    }
                });
        });
    }
    
    function updateAlternative(e) {
        e.preventDefault();
        
        var $modal = $('#create-alternative-popup');
        
        var alternativeId = $modal.data('id');
        
        var formData = $modal.find('form').serialize();
        
        $.post(getUrlPrefix() + '/alternative/' + alternativeId, formData)
            .done(function(response) {
                app.components.NotificationArea.clear();
                if (response.error) {
                    app.components.NotificationArea.showErrorInModal($modal, 'Alternative was not updated');
                    app.components.FormUtil.displayErrors($informationForm, response.fieldErrorMessages);
                } else {
                    app.components.NotificationArea.showSuccess('Alternative was updated');
                    refreshAlternativesList(response.data);
                    
                    $modal.modal('hide');
                }
            });
        
    }
    
    function onAddAlternativeClick() {
        var modalContent = app.templates['templates/popup/createAlternative']({
            title : 'Create Alternative'
        });
        var $modal = $(modalContent);
        $modal.find('#create-alternative-save-btn').click(createAlternative);
        $modal.on('hidden.bs.modal', function() {
            $modal.remove();
        });
        $modal.modal();
    }

    function createAlternative(e) {
        e.preventDefault();
        
        var $modal = $('#create-alternative-popup');
        var $form = $modal.find('form');
        var formData = $form.serialize();
        
        app.components.NotificationArea.clear();
        app.components.FormUtil.resetErrors($form);
        
        $.post(getUrlPrefix() + '/alternative', formData)
            .done(function(response){
                if (response.error) {
                    if (response.fieldErrorMessages) {
                        app.components.FormUtil.displayErrors($form, response.fieldErrorMessages);
                    } 
                    if (response.errorMessage) {
                        app.components.NotificationArea.showErrorInModal($modal, response.errorMessage);
                    }
                } else {
                    app.components.NotificationArea.showSuccess('Alternative created');
                    refreshAlternativesList(response.data);
                    
                    $modal.modal('hide');
                }
            });
    }
    
    function getUrlPrefix() {
        return app.config.ctx + '/project/' + projectId + '/decision/' + projectDecisionId;
    }
    
    function refreshAlternativesList(alternatives) {
        var $alternativeList = $('.js-alternative-list');
        var $caption = $('#tab-alternatives').find('.js-alternative-list-caption');
        
        if (alternatives && alternatives.length > 0) {
            var alternativeListContents = app.templates['templates/projectDecision/alternativeList'](alternatives);
            var $updatedAlternativeList = $(alternativeListContents);
            $alternativeList.replaceWith($updatedAlternativeList);
            $caption.text('List of alternatives');
            setAlternativeListHandlers();
        } else {
            $alternativeList.empty();
            $alternativeList.hide();
            $caption.text('List of alternatives is empty. Please add some alternative to perform decision from.');
        }
    }
    
    app.fragments.EditProjectDecisionAlternatives = {
        render : render
    }

})();