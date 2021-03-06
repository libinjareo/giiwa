window.portlet = {};
portlet.extend = function(m) {
	for ( var k in m) {
		portlet[k] = m[k];
	}
};
portlet.extend({
	that : false,

	load : function(panel, cb) {
		panel.find('.portlet').each(function(i, e) {
			e = $(e);
			if (e.attr('data-loaded') != '1') {
				var uri = e.attr('data-uri');
				if (uri && uri.length > 0) {
					$.get(uri, function(d) {
						e.html(d);
						e.attr('data-loaded', '1');
						cb && cb(e);
					})
				}
			}
		})
	}
});

$(function() {
	giiwa.css('/css/portlet.css');
})
