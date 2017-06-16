(function() {
    
    var OPPOSITE_VALUES_MAP = {
        SLIGHTLY_FAVORS: 'SLIGHTLY_CONCEDE',
        STRONGLY_FAVORS: 'STRONGLY_CONCEDE',
        VERY_STRONGLY_FAVORS: 'VERY_STRONGLY_CONCEDE',
        EXTREME_FAVORS: 'EXTREME_CONCEDE',
        SLIGHTLY_CONCEDE: 'SLIGHTLY_FAVORS',           
        STRONGLY_CONCEDE: 'STRONGLY_FAVORS',         
        VERY_STRONGLY_CONCEDE: 'VERY_STRONGLY_FAVORS',
        EXTREME_CONCEDE: 'EXTREME_FAVORS',
        EQUAL: 'EQUAL'
    };
    
    function ComparisonTable(comparisonTableData) {
        this.comparisonTableData = comparisonTableData;
        
        this.$comparisonTable = $(app.templates['templates/components/comparisonTable']({
            comparisonsData : comparisonTableData
        }));
        
        var that = this;
        
        this.$comparisonTable.find('.js-alternative-dropdown .dropdown-menu a').click(function() {
            var dropDownMenuVariant = $(this);
            processUpdateDropDown(dropDownMenuVariant);
            
            if (that.tableUpdatedHandler) {
                that.tableUpdatedHandler();
            }
        });
    }
    
    function processUpdateDropDown(dropDownMenuVariant) {
        var dropDown = dropDownMenuVariant.closest('.js-alternative-dropdown');
        var value = dropDownMenuVariant.data('value');
        var text = dropDownMenuVariant.text();
        
        updateDropDown(dropDown, value, text);
        
        var oppositeDropDown = getOppositeDropDown(dropDown);
        var oppositeValue = OPPOSITE_VALUES_MAP[value];
        var oppositeText = app.localization.comparisonTable.scale[oppositeValue];
        
        updateDropDown(oppositeDropDown, oppositeValue, oppositeText);
    }
    
    function getOppositeDropDown($dropDown) {
        var alternativeA = $dropDown.data('alternative-a'),
            alternativeB = $dropDown.data('alternative-b');
        return $dropDown.closest('.js-comparisons-table')
                        .find('.js-alternative-dropdown')
                        .filter('[data-alternative-a="' + alternativeB + '"]')
                        .filter('[data-alternative-b="' + alternativeA + '"]');        
    }
    
    function updateDropDown($dropDown, value, text) {
        $dropDown.data('value', value);
        $dropDown.find('.dropdown-toggle span').first().text(text);
    }
    
    ComparisonTable.prototype.getElement = function() {
        return this.$comparisonTable;
    };
    
    ComparisonTable.prototype.setTableUpdatedHandler = function(handler) {
        this.tableUpdatedHandler = handler;
    };
    
    ComparisonTable.prototype.getValues = function() {
        var comparisons = [];
        
        this.$comparisonTable.find('.js-alternative-dropdown').each(function() {
            var $cell = $(this);
            comparisons.push({
                value : $cell.data('value'),
                id : $cell.data('id')
            });
        });
        
        return comparisons;
    };

    app.objects.ComparisonTable = ComparisonTable;

})();