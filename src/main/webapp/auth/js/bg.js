/**
 * jQuery.fullBg
 * Version 1.0
 * Copyright (c) 2010 c.bavota - http://bavotasan.com
 * Dual licensed under MIT and GPL.
 * Date: 02/23/2010
**/
(function($) {
  $.fn.fullBg = function(){
    var bgImg = $(this);		
 
    function resizeImg() {
      var imgwidth = bgImg.width();
      var imgheight = bgImg.height();
        
      var winwidth = $(window).width();
      var winheight = $(window).height();
    
      var widthratio = winwidth / imgwidth;
      var heightratio = winheight / imgheight;
 
      var widthdiff = heightratio * imgwidth;
      var heightdiff = widthratio * imgheight;
      bgImg.css({
          width: '100%',
          height: winheight+'px'
	  //height: '80%'
        });
      //if(heightdiff>winheight) {
      //  bgImg.css({
      //    width: '100%',
      //    height: heightdiff+'px'
      //  });
      //} else {
      //  bgImg.css({
      //    width: '100%',
      //    height: winheight+'px'
      //  });		
      //}
    } 
    resizeImg();
    $(window).resize(function() {
      resizeImg();
    }); 
  };
})(jQuery)