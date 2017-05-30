window.app = window.app || {};
window.app.templates = window.app.templates || {};window.app.templates["templates/part/comparisonTable"] = (function(it){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
__p+='';
return __p;
});
window.app.templates["templates/popup/createAlternative"] = (function(it){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
__p+='<div class="modal-dialog js-create-alternative-popup">\r\n  <div class="modal-content">\r\n    <div class="modal-header">\r\n          <button type="button" class="close" data-dismiss="modal">&times;</button>\r\n          <h4 class="js-edit-alternative-popup-title">\r\n            '+
((__t=( it.title || 'Create' ))==null?'':__t)+
'\r\n          </h4>\r\n    </div>\r\n    <div class="modal-body">\r\n        <form>\r\n          <div class="form-group">\r\n            <label for="input-alternative-popup-name">Name</label>\r\n            <input type="text" name="name" class="form-control" id="input-alternative-popup-name" placeholder="please specify alternative name">\r\n          </div>\r\n          <div class="form-group">\r\n            <label for="input-alternative-popup-description">Description</label>\r\n            <textarea name="description" class="form-control" id="input-alternative-popup-description" placeholder="please specify alternative description"></textarea>\r\n          </div>\r\n        </form>\r\n    </div>\r\n    <div class="modal-footer">\r\n          <button id="create-alternative-save-btn" type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal">\r\n            <span class="glyphicon glyphicon-plus"></span> Save\r\n          </button>\r\n          <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal">\r\n            <span class="glyphicon glyphicon-ban-circle"></span> Cancel\r\n          </button>\r\n    </div>\r\n  </div>\r\n</div>';
return __p;
});
window.app.templates["templates/popup/createCriteria"] = (function(it){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
__p+='';
 // it.title  
__p+='\r\n';
 // it.criteria  
__p+='\r\n\r\n';
 it.criteria = it.criteria || {}; 
__p+='\r\n\r\n<div class="modal-dialog">\r\n  <div class="modal-content">\r\n    <div class="modal-header">\r\n          <button type="button" class="close" data-dismiss="modal">&times;</button>\r\n          <h4 class="js-edit-criteria-popup-title">\r\n            '+
((__t=( it.title || 'Create' ))==null?'':__t)+
'\r\n          </h4>\r\n    </div>\r\n    <div class="modal-body">\r\n        <form>\r\n          <div class="form-group">\r\n            <label for="input-criteria-popup-name">Name:</label>\r\n            <input type="text" name="name" class="form-control" id="input-criteria-popup-name" placeholder="please specify criteria name">\r\n          </div>\r\n          <div class="form-group">\r\n            <label for="input-criteria-popup-description">Description:</label>\r\n            <input type="text" name="description" class="form-control" id="input-criteria-popup-description" placeholder="please specify criteria description">\r\n          </div>\r\n        </form>\r\n    </div>\r\n    <div class="modal-footer">\r\n          <button id="criteria-popup-save-btn" type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal">\r\n            <span class="glyphicon glyphicon-ok-sign"></span> Save\r\n          </button>\r\n          <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal">\r\n            <span class="glyphicon glyphicon-ban-circle"></span> Cancel\r\n          </button>\r\n    </div>\r\n  </div>\r\n</div>';
return __p;
});
window.app.templates["templates/popup/editComparisonResult"] = (function(it){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
__p+='';
return __p;
});
