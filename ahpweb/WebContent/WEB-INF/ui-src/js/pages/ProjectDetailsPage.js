(function() {

    function render() {
        expandFirstActiveDecision();
        setupCollapseHeaderClick();
    }
    
    function expandFirstActiveDecision() {
        $('#activeDecisionsAccordion')
            .find('.panel-collapse').eq(0).collapse('toggle');
    }
    
    function setupCollapseHeaderClick() {
        $('.js-accordion-panel').click(function() {
           $(this).find('.panel-collapse').collapse('toggle');
        });
    }

    app.pages.ProjectDetailsPage = {
        render : render
    };

})();