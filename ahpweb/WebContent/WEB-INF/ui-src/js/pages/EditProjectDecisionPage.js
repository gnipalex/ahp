(function() {

    var projectId,
        projectDecisionId;
   
    function render() {
        projectId = $('#edit-project-decision-data-element').data('project-id');
        projectDecisionId = $('#edit-project-decision-data-element').data('decision-id');
        
        app.fragments.EditProjectDecisionBasicInformation.render();
        app.fragments.EditProjectDecisionAlternatives.render();
        app.fragments.EditProjectDecisionCriterias.render();
        // edit experts (voters)
        
        $('#edit-decision-next-step-btn').click(showNextStepPopup);

    }
    
    function showNextStepPopup() {
        var $modal = $(app.templates['templates/popup/editDecisionNextStep']());
        
        $modal.on('hidden.bs.modal', function() {
            $modal.remove();
        });
        
        $modal.find('#confirm-btn').click(function() {
            var postForm = $('<form>')
                .attr('method', 'post')
                .attr('action', getUrlPrefix() + '/finishEdit')
                .appendTo($('body'));
            postForm.submit();
        });
       
        $modal.modal();
    }
    
    function getUrlPrefix() {
        return app.config.ctx + '/project/' + projectId + '/decision/' + projectDecisionId;
    }

    app.pages.EditProjectDecisionPage = {
        render : render
    };

})();