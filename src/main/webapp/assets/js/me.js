
$(document).ready(function(){
    $.get('rest/rating', function(res) {

        new Morris.Line({
              element: 'myfirstchart',
              data: res,
              // The name of the data record attribute that contains x-ratings.
              xkey: 'date',
              ykeys: ['rating'],
              labels: ['Rating'],
              ymax: 5,
              ymin: 0
        });

    });


});
