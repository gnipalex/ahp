(function() {
   
    var projectId,
        projectDecisionId;
   
    function render() {
        projectId = $('#edit-project-decision-data-element').data('project-id');
        projectDecisionId = $('#edit-project-decision-data-element').data('decision-id');
        
        $('.js-add-criteria').click(onAddCriteriaClick);
        
        setCriteriaListHandlers();
    }
    
    function setCriteriaListHandlers() {
        var $criteriaItems = $('.js-criteria-list .js-criteria');
        
        $criteriaItems.find('.js-edit-criteria').click(function() {
            var $criteriaItem = $(this).closest('.js-criteria');
 
            var modalContent = app.templates['templates/popup/createCriteria']({
                title : 'Update criteria',
                name : $criteriaItem.data('name'),
                description : $criteriaItem.data('description'),
                id : $criteriaItem.data('id')
            });
            
            var $modal = $(modalContent);
            $modal.on('hidden.bs.modal', function() {
                $modal.remove();
            });
            
            $modal.find('#create-criteria-save-btn').click(updateCriteria);
            
            $modal.modal();
        });
        
        $criteriaItems.find('.js-remove-criteria').click(function() {
            var $criteriaItem = $(this).closest('.js-criteria');
            var criteriaId = $criteriaItem.data('id');
            $.post(getUrlPrefix() + '/criteria/' + criteriaId + '/delete')
                .done(function(response) {
                    if (response.error) {
                        app.components.NotificationArea.showError('Criteria was not removed');
                    } else {
                        app.components.NotificationArea.showSuccess('Criteria was removed');
                        $criteriaItem.remove();
                    }
                });
        });
    }
    
    function updateCriteria(e) {
        e.preventDefault();
        
        var $modal = $('#create-criteria-popup');
        
        var criteriaId = $modal.data('id');
        
        var formData = $modal.find('form').serialize();
        
        $.post(getUrlPrefix() + '/criteria/' + criteriaId, formData)
            .done(function(response) {
                if (response.error) {
                    app.components.NotificationArea.showErrorInModal($modal, 'Criteria was not updated');
                    app.components.FormUtil.displayErrors($informationForm, response.fieldErrorMessages);
                } else {
                    app.components.NotificationArea.showSuccess('Criteria was updated');
                    refreshCriteriasList(response.data);
                    
                    $modal.modal('hide');
                }
            });
        
    }
    
    function onAddCriteriaClick() {
        var modalContent = app.templates['templates/popup/createCriteria']({
            title : 'Create criteria'
        });
        var $modal = $(modalContent);
        $modal.find('#create-criteria-save-btn').click(createCriteria);
        $modal.on('hidden.bs.modal', function() {
            $modal.remove();
        });
        $modal.modal();
    }

    function createCriteria(e) {
        e.preventDefault();
        
        var $modal = $('#create-criteria-popup');
        var $form = $modal.find('form');
        var formData = $form.serialize();
        
        app.components.NotificationArea.clear();
        app.components.FormUtil.resetErrors($form);
        
        $.post(getUrlPrefix() + '/criteria', formData)
            .done(function(response){
                if (response.error) {
                    if (response.fieldErrorMessages) {
                        app.components.FormUtil.displayErrors($form, response.fieldErrorMessages);
                    } 
                    if (response.errorMessage) {
                        app.components.NotificationArea.showErrorInModal($modal, response.errorMessage);
                    }
                } else {
                    app.components.NotificationArea.showSuccess('Criteria created');
                    refreshCriteriasList(response.data);
                    
                    $modal.modal('hide');
                }
            });
    }
    
    function getUrlPrefix() {
        return app.config.ctx + '/project/' + projectId + '/decision/' + projectDecisionId;
    }
    
    function refreshCriteriasList(criterias) {
        var criteriaListContents = app.templates['templates/projectDecision/criteriaList'](criterias);
        var $updatedCriteriaList = $(criteriaListContents);
        // doesn't work when no element on page
        var $criteriaList = $('.js-criteria-list').replaceWith($updatedCriteriaList);
        setCriteriaListHandlers();
    }

    app.fragments.EditProjectDecisionCriterias = {
        render : render
    }
   
})();