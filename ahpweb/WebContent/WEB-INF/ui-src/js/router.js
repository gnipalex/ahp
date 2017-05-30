$(function() {
    var pageName = $(body).data('page-name');
    
    var pageRenderer = app.pages[pageName];
    
    if (pageRenderer) {
        pageRenderer.render();
    }
});