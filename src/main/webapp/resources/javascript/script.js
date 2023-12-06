function performSearch(event) {
    if (event) {
        event.preventDefault();
    }

    console.log("Search function triggered");
    var input, filter, tables, tr, td, i, j, txtValue, found;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    tables = document.getElementsByTagName("table");

    //loops through all tables
    for (var k = 0; k < tables.length; k++) {
        var anyVisibleRows = false;
        tr = tables[k].getElementsByTagName("tr");

        //loops through all the rows in current table
        for (i = 1; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td");
            found = false;
            //searches through all the columns
            for (j = 0; j < td.length; j++) {
                if (td[j]) {
                    txtValue = td[j].textContent || td[j].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        found = true;
                        break; 
                    }
                }
            }
            tr[i].style.display = found ? "" : "none";
            anyVisibleRows = anyVisibleRows || found;
        }

        //this code works to hide the tables that did not yield resuts
        
        var tableContainer = tables[k].parentElement;
        if (anyVisibleRows) {
            tableContainer.style.display = "";
        } else {
            tableContainer.style.display = "none";
            if (tableContainer.previousElementSibling && tableContainer.previousElementSibling.tagName === "H2") {
                tableContainer.previousElementSibling.style.display = "none";
            }
        }
    }
}
