$(function() {
	var updateComparisonsTable = $('.js-criteria-comparisons-table');
	if (updateComparisonsTable.length) {
		var allCriteriaDropdowns = $('.js-criteria-dropdown');
		allCriteriaDropdowns.find('.dropdown-menu a').click(function() {
			var dropDownToggleButton = $(this).closest('.js-criteria-dropdown').find('.dropdown-toggle');
			dropDownToggleButton
				.data('value',  $(this).data('value'))
				.find('span').first().text($(this).text());
		});
		
		$('.js-update-criteria-comparisons').click(function() {
			var data = [];
			dropDownToggleButton.each(function() {
				data.push({
					alternativeA : $(this).data('alternative-a'),
					alternativeB : $(this).data('alternative-b'),
					value : $(this).data('value'),
				});
			});
			$.post(location.href, data).done(function() {
				// refresh page
				location.href = location.href;
			});
			
		});
	}
	
});