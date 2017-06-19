(function () {
    
    var projectId,
        projectDecisionId;
    
    function render() {
        projectId = $('#project-decision-data-element').data('project-id');
        projectDecisionId = $('#project-decision-data-element').data('decision-id');
        
        loadAndRenderAlternativeComparisons();
    }
    
    function loadAndRenderAlternativeComparisons() {
        $.getJSON(getUrlPrefix() + '/loadAlternativeComparisons')
        .done(function(response) {
            if (response.data) {
                _.each(response.data, renderAlternativeComparison);
            }
        });
    }
    
    function renderAlternativeComparison(alternativeComparison) {
        var $criteriaPill = $(app.templates['templates/components/alternativeComparisonPill'](alternativeComparison.criteria));
        $('.js-alternative-pills').append($criteriaPill);
        
        var $alternativeComparisonTab = $(app.templates['templates/components/alternativeComparisonTab'](alternativeComparison));
        
        var comparisonTable = new app.objects.ComparisonTable(alternativeComparison);
        
        comparisonTable.setTableUpdatedHandler(function() {
            $alternativeComparisonTab.find('.js-update-comparison').removeAttr('disabled');
        });
        
        $alternativeComparisonTab.data('comparison-table-object', comparisonTable);
        
        $alternativeComparisonTab.find('.comparison-table').append(comparisonTable.getElement());
        
        $alternativeComparisonTab.find('.js-update-comparison').click(updateAlternativeComparison);
        
        $('.js-alternative-comparison-tabs').append($alternativeComparisonTab);
    }
    
    function updateAlternativeComparison() {
        var $updateComparisonButton = $(this);
        var $alternativeComparisonTab = $updateComparisonButton.closest('.js-alternative-tab');
        
        var comparisonId = $alternativeComparisonTab.data('comparison-id');
        var values = $alternativeComparisonTab.data('comparison-table-object').getValues();
        
        $.ajax({
            type: 'post',
            url: getUrlPrefix() + '/alternativeComparison/' + comparisonId,
            data : JSON.stringify({ comparisons : values }),
            contentType: 'application/json',
            dataType: 'json'
        }).done(function(response) {
            if (response.error) {
                app.components.NotificationArea.showError('Comparison was not updated : ' + response.errorMessage);
            } else {
                //$accordionItem.removeClass('panel-success').removeClass('panel-danger')
                //$accordionItem.addClass(response.data.consistent ? 'panel-success' : 'panel-danger');
                
                $alternativeComparisonTab.find('.js-is-consistent').text(response.data.consistent ? "Yes" : "No");
                $alternativeComparisonTab.find('.js-consistency-ratio').text(response.data.consistencyRatio);
                
                $alternativeComparisonTab.find('.js-update-comparison').attr('disabled', true);

            }
        });
    }
    
    function getUrlPrefix() {
        return app.config.ctx + '/project/' + projectId + '/decision/' + projectDecisionId;
    }

    app.pages.AlternativeComparisonPage = {
        render : render
    }
    
})();