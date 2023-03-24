$(document).ready( function () {
    $('#usersTable').DataTable({
        "paging": true,
        "bInfo": false,
        "language": {
            "lengthMenu":         "Mutass:  _MENU_  elemet",
            "zeroRecords":        "Nincs találat!",
            "info":               "",
            "infoEmpty":          "Nincs megjeleníthető elem",
            "infoFiltered":       "(szűrve ennyiből: _MAX_)",
            "search":             "Keresés:",
            "loadingRecords":     "Betöltés...",
            "processing":         "Folyamatban...",
            "paginate": {
                "first":              "Első",
                "last":               "Utolsó",
                "next":               "Következő",
                "previous":           "Előző"
            }
        }
    });
    var table = $('#usersTable').DataTable();
    $('#usersTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }


        if (table.rows('.selected').data().length == 0) {
            document.getElementById("userListEdit").href="javascript:noSelectedElement()";
            document.getElementById("userListRemove").href="javascript:noSelectedElement()";
        } else {
            document.getElementById("userListEdit").href="/admin/user/"+table.rows('.selected').data()[0][0]+"/edit";
            document.getElementById("userListRemove").href="/admin/user/remove/"+table.rows('.selected').data()[0][0];
        }
    });
});


$(document).ready(function () {
    var table = $('#fault').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });

    $('#fault tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }

        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/fault/' + id;
        $("#frameFault").load(url,$("#frameFault").serialize());
    });
});

$(document).ready(function () {
    var table = $('#service').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });

    $('#service tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/services/' + id;
        $("#frameService").load(url,$("#frameService").serialize());
    });
});

$(document).ready(function () {
    var table = $('#product').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });
    $('#product tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }

        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/products/' + id;
        $("#frameProduct").load(url,$("#frameProduct").serialize());
    });
});

$(document).ready(function () {
    var table = $('#object').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });
    $('#object tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }

        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/items/' + id;
        $("#frameObject").load(url,$("#frameObject").serialize());
    });
});

$(document).ready(function () {
    var table = $('#listForWorksheet').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });
    $('#listForWorksheet tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/' + id;
        $("#frameWorksheet").load(url,$("#frameWorksheet").serialize());
    });
});

$(document).ready(function () {
    var table = $('#customer').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });
    $('#customer tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/customers/' + id;


        $("#frameCustomer").load(url,$("#frameCustomer").serialize());
    });
});

$(document).ready(function () {
    var table = $('#dealer').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });
    $('#dealer tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/dealers/' + id;


        $("#frameDealer").load(url,$("#frameDealer").serialize());
    });
});


$(document).ready(function () {
    var table = $('#warehouse').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });
    $('#warehouse tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }
        var url = '/worksheet/warehouse/' + id;
        $("#frameWarehouse").load(url,$("#frameWarehouse").serialize());
    });
});


$(document).ready(function () {
    var table = $('#colleagues').DataTable({
    "paging": true,
    "bInfo": true,
    "language": {
        "lengthMenu":         "Mutass:  _MENU_  elemet",
        "zeroRecords":        "Nincs találat!",
        "info":               "",
        "infoEmpty":          "Nincs megjeleníthető elem",
        "infoFiltered":       "(szűrve ennyiből: _MAX_)",
        "search":             "Keresés:",
        "loadingRecords":     "Betöltés...",
        "processing":         "Folyamatban...",
        "paginate": {
            "first":              "Első",
            "last":               "Utolsó",
            "next":               "Következő",
            "previous":           "Előző"}}
    });
    $('#colleagues tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        var id = -1;
        try {
          id = table.rows('.selected').data()[0][0];
        } catch (error) {

        }

        var url = '/super-user/settings/workers/' + id;
        $("#frameWorker").load(url,$("#frameWorker").serialize());
    });
});