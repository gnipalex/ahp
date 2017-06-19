(function() {
    
    var projectId,
        projectDecisionId;
    
    function render() {
        projectId = $('#project-decision-data-element').data('project-id');
        projectDecisionId = $('#project-decision-data-element').data('decision-id');
        
        $('#js-add-criteria-comparison-btn').click(onAddCriteriaComparison);
        
        $('#compare-criterias-next-step-btn').click(onNextStepButtonClick);
        
        loadAndRenderCriteriaComparisons();
    }
    
    function onNextStepButtonClick() {
        var $modal = $(app.templates['templates/popup/compareCriteriasNextStep']());
        
        $modal.on('hidden.bs.modal', function() {
            $modal.remove();
        });
        
        $modal.find('#confirm-btn').click(function() {
            $('<form>')
                .attr('method', 'post')
                .attr('action', getUrlPrefix() + '/finishCriteriaComparison')
                .appendTo($('body'))
                .submit();
        });
        
        $modal.modal();
    }
    
    function loadAndRenderCriteriaComparisons() {
        $.getJSON(getUrlPrefix() + '/criteriaComparison').done(function(response) {
            if (response.data) {
                _.each(response.data, addCriteriaComparisonToList);
            }
        });
    }
    
    function onAddCriteriaComparison() {
        var modalContents = app.templates['templates/popup/createCriteriaComparison']({});
        
        var $modal = $(modalContents);
        
        $modal.find('#create-criteria-comparison-save-btn').click(function() {
            var formData = $modal.find('form').serialize();
            
            $.post(getUrlPrefix() + '/criteriaComparison', formData)
            .done(function(response) {
                app.components.NotificationArea.clear();
                if (response.error) {
                    app.components.NotificationArea.showErrorInModal($modal, 'Criteria comparison was not created');
                    app.components.FormUtil.displayErrors($informationForm, response.fieldErrorMessages);
                } else {
                    app.components.NotificationArea.showSuccess('Criteria comparison was created');
                    addCriteriaComparisonToList(response.data);
                    
                    $modal.modal('hide');
                }
            });
            
        });
        
        $modal.on('hidden.bs.modal', function() {
            $modal.remove();
        });
        
        $modal.modal();
    }
    
    function addCriteriaComparisonToList(criteriaComparisonTableData) {
        var $accordionItem = createAccordionItem(criteriaComparisonTableData);
        
        $accordionItem.find('.js-update-comparison').click(function() {
            var values = $accordionItem.data('comparison-table-object').getValues();
            var comparisonId = $accordionItem.data('comparison-id');
            
            $.ajax({
                type: 'post',
                url: getUrlPrefix() + '/criteriaComparison/' + comparisonId,
                data : JSON.stringify({ comparisons : values }),
                contentType: 'application/json',
                dataType: 'json'
            }).done(function(response) {
                if (response.error) {
                    app.components.NotificationArea.showError('Comparison was not updated : ' + response.errorMessage);
                } else {
                    $accordionItem.removeClass('panel-success').removeClass('panel-danger')
                    $accordionItem.addClass(response.data.consistent ? 'panel-success' : 'panel-danger');
                    
                    $accordionItem.find('.js-is-consistent').text(response.data.consistent ? "Yes" : "No");
                    $accordionItem.find('.js-consistency-ratio').text(response.data.consistencyRatio);
                    
                    $accordionItem.find('.js-update-comparison').attr('disabled', true);
                }
            });
            
        });
        
        $('#criteria-comparison-accordion').append($accordionItem);
    }
    
    function createAccordionItem(criteriaComparisonTableData) {
        var $accordionItem = $(app.templates['templates/components/comparisonAccordionItem']({
            accordionId : 'criteria-comparison-accordion',
            comparisonTableData : criteriaComparisonTableData,
            hasDescription : true
        }));
        
        var comparisonTable = new app.objects.ComparisonTable(criteriaComparisonTableData);
        
        comparisonTable.setTableUpdatedHandler(function() {
            $accordionItem.find('.js-update-comparison').removeAttr('disabled');
        });
        
        $accordionItem.data('comparison-table-object', comparisonTable);
        
        $accordionItem.find('.criteria-comparison-table').append(comparisonTable.getElement());
        
        return $accordionItem;
    }
    
    function getUrlPrefix() {
        return app.config.ctx + '/project/' + projectId + '/decision/' + projectDecisionId;
    }
    
    app.pages.CriteriaComparisonPage = {
        render : render
    }
    
})();