$(function() {
         var productID ="";

		  var categoryName ="";
		 var brandName="";
		 var titleName="";
		 
		  var myChart;
         var urlName="http://localhost:8080/api/product/findDistinctByCategory";
             $("#category").autocomplete({
				 minLength: 3,
                 source: function (request, response) {
                 $.ajax({
                     method: "GET",
                     dataType: "json",
                     url: urlName +'?category='+request.term,
                     success: function (data) {
                         var transformed = $.map(data, function (el) {
                          return {
                          label: el.value
                          };
                          });
                          response(transformed);
                     },
                     error: function () {
                         response([]);
                     }
                 });
             }
             });
         console.log(urlName+'?category='+$('#category').val());
             $("#brand").autocomplete({
				 minLength: 1,
                 source: function (request, response) {
                 $.ajax({
                     method: "GET",
                     dataType: "json",
                     url: urlName+'AndBrand'+'?category='+$('#category').val()+'&brand='+request.term,
                     success: function (data) {
                         var transformed = $.map(data, function (el) {
                          return {
                          label: el.value
                          };
                          });
                          response(transformed);
                     },
                     error: function () {
                         response([]);
                     }
                 });
             }
             });
         console.log(urlName+'AndBrand'+'?category='+$('#category').val()+'&brand='+$('#brand').val());
             $("#title").autocomplete({
				   minLength: 1,
                   source: function (request, response) {
                 $.ajax({
                     method: "GET",
                     dataType: "json",
                     url: 'http://localhost:8080/api/product/findFirst10ByCategoryAndBrandAndTitle'+'?category='+$('#category').val()+'&brand='+$('#brand').val()+'&title='+request.term,
                     success: function (data) {
                         var transformed = $.map(data, function (el) {
						  $("#selectedProduct").val(el.label);
                          return {
                          label: el.value,
                          id: el.label
                          };
                          });
                          response(transformed);
                     },
                     error: function () {
                         response([]);
                     }
                 });
         
             }
             });
         console.log('http://localhost:8080/api/product/findFirst10ByCategoryAndBrandAndTitle'+'?category='+$('#category').val()+'&brand='+$('#brand').val()+'&title='+$('#title').val());
         
             $.cascadingAutocompletes(["#category", "#brand", "#title"]);
         
        		
		 
	jQuery('#search').on('click', function(event) {    
		  categoryName=$('#category').val();
		  brandName=$('#brand').val();
		  titleName=$('#title').val();
           if(categoryName!=""||brandName!=""||titleName!=""){
		       $('#productsTable').bootstrapTable('refresh', {
				url: 'http://localhost:8080/api/product/findAllWithIgnoreCase?category='+categoryName+'&brand='+brandName+'&title='+titleName});
				$('#productsDiv').show();		   
//		      drawProductChart(productID);
//            $('#myModal').modal('show');
 
           }

           else {
		   
		    $.growl.warning({ message: "Please enter a category!" });
		   
		   }		   
         }); 
		 
		 
	jQuery('#drawChart').on('click', function(event) {    
		 var drawChartURL=$('#tableSelectedProduct').val();
           if(drawChartURL!=""){   
		    // drawProductChart(drawChartURL);
             $('#showChartModal').modal('show');
 
           }  

    else {
		   
		    $.growl.error({ message: "No item selected to draw!" });
		   
		   }		   
         }); 		 
		 
		 		
       
         jQuery('#closeButton').on('click', function(event) {    
		       clearSearcFields();
			   clearChartData();  
			   
			   
         }); 
		 
//highlight selected tr.		 
jQuery('#productsTable tbody').on( 'click', 'tr', function () {
   if($(this).hasClass('highlight')){
        $(this).removeClass('highlight');
		
		$('#tableSelectedProduct').val("");
		$('#tableSelectedProduct').hide();

		}
    else {
        $(this).addClass('highlight').siblings().removeClass('highlight');
		
		$('#tableSelectedProduct').val($(this).closest('tr').find('td:first').text());
		$('#tableSelectedProduct').show();
		}
} ); 
		 

	 

function clearSearcFields(){

$('#category').val("");
$('#brand').val("");
$('#title').val("");
$("#selectedProduct").val();
}		 
		 
function clearChartData(){

myChart.series[0].remove(true);

};

		 
 function drawProductChart(drawChartURL){
 
     $.getJSON('http://localhost:8080/api/product/findByProductUrl?url='+drawChartURL, function (data) {
		 var chartDataArr =[];
		data.map(function (item) {
        var tempArr =[];
		tempArr.push(Number(item.date))
		tempArr.push(Number(item.price));
		chartDataArr.push(tempArr);
        return '[' + new Date(Number(item.date)).toUTCString() + ': ' + item.price +']';
      });
	  
myChart =Highcharts.chart('chartDiv', {
                 chart: {
                     zoomType: 'x'
                 },
                 title: {
                     text: 'Date vs Price for product rate over time'
                 },
                 subtitle: {
                     text: document.ontouchstart === undefined ?
                             'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                 },
                 xAxis: {
                     type: 'datetime'
                 },
                 yAxis: {
         			type: 'number',
                     title: {
                         text: 'Product Price'
                     }
                 },
                 legend: {
                     enabled: true
                 },
                 plotOptions: {
                     area: {
                         fillColor: {
                             linearGradient: {
                                 x1: 0,
                                 y1: 0,
                                 x2: 0,
                                 y2: 1
                             },
                             stops: [
                                 [0, Highcharts.getOptions().colors[0]],
                                 [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                             ]
                         },
                         marker: {
                             radius: 2
                         },
                         lineWidth: 1,
                         states: {
                             hover: {
                                 lineWidth: 1
                             }
                         },
                         threshold: null
                     }
                 },
         
                 series: [{
                     type: 'area',
                     name: 'Price',
                     data: chartDataArr
                 }]
             });
			 
         });
 
 };		 
         

		          });