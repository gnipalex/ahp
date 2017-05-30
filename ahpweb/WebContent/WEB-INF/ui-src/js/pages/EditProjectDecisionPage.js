(function() {
	
	function render() {
		$('.js-add-alternative').click(onAddAlternativeClick);
	}
    
    function onAddAlternativeClick() {
        var $modal = app.templates['templates/popup/createAlternative']({
           title: 'Create Alternative' 
        });
        $modal.find('#create-alternative-save-btn').click(createAlternative);
    }
    
    function createAlternative(e) {
        
        
        
        e.stopPropagation();
    }
	
	global.app.pages.EditProjectDecisionPage = {
		render : render
	};
	
})();