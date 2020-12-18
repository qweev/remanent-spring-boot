$(document).ready(function(){
    $("th").hover(function(){
        $(this).css("background-color", "#E6E6E6");
        }, function(){
        $(this).css("background-color", "white");
      });
});


$.fn.sortTableByNames = function(tableId, columnId) {
console.log("col ifd" + columnId);
console.log("table ifd" + tableId);
  var table, rows, changePosition, i, row, next_row, shouldChangeOrder, sortingOrder, sortcount = 0;
  table = document.getElementById(tableId);
  sortingOrder = "asc";
  changePosition = true;

  while (changePosition) {
    changePosition = false;
    rows = table.rows;
    for (i = 1; i < (rows.length - 1); i++) {
      shouldChangeOrder = false;
      row = rows[i].getElementsByTagName("td")[columnId];
      next_row = rows[i + 1].getElementsByTagName("td")[columnId];
      if ( (sortingOrder == "asc") && (row.innerHTML.toLowerCase().length > 0) && (next_row.innerHTML.toLowerCase().length >0 ) ) {
        if (row.innerHTML.toLowerCase() > next_row.innerHTML.toLowerCase()) {
          shouldChangeOrder = true;
          break;
        }
      } else if ( (sortingOrder == "desc") && (row.innerHTML.toLowerCase().length > 0) && (next_row.innerHTML.toLowerCase().length >0 ) ) {
        if (row.innerHTML.toLowerCase() < next_row.innerHTML.toLowerCase()) {
          shouldChangeOrder = true;
          break;
        }
      }
    }

    if (shouldChangeOrder) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      changePosition = true;
      sortcount ++;
    } else {
      if (sortcount == 0 && sortingOrder == "asc") {
        sortingOrder = "desc";
        changePosition = true;
      }
    }
  }

}


$.fn.sortTableByNumbers = function(tableId, columnId) {
console.log("col ifd" + columnId);
console.log("table ifd" + tableId);
  var table, rows, changePosition, i, row, next_row, shouldChangeOrder, sortingOrder, sortcount = 0;
  table = document.getElementById(tableId);
  sortingOrder = "asc";
  changePosition = true;

  while (changePosition) {
    changePosition = false;
    rows = table.rows;

console.log("tab row len "+ rows.length);
    for (i = 1; i < (rows.length - 1); i++) {
      shouldChangeOrder = false;
      row = rows[i].getElementsByTagName("td")[columnId];
      next_row = rows[i + 1].getElementsByTagName("td")[columnId];
      if (sortingOrder == "asc") {
        if (parseInt(row.innerHTML, 10) > parseInt(next_row.innerHTML,10)) {
          shouldChangeOrder = true;
          break;
        }
      } else if (sortingOrder == "desc") {
        if (parseInt(row.innerHTML, 10) < parseInt(next_row.innerHTML,10)) {
          shouldChangeOrder = true;
          break;
        }
      }
    }

    if (shouldChangeOrder) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      changePosition = true;
      sortcount ++;
    } else {
      if (sortcount == 0 && sortingOrder == "asc") {
        sortingOrder = "desc";
        changePosition = true;
      }
    }
  }

}