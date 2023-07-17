const search = document.querySelector('.input-group input');
const tableRows = document.querySelectorAll('tbody tr');
const tableHeadings = document.querySelectorAll('thead th');
const exportButton = document.getElementById('toPDF');

//1. Pesquisar dados na tabelaa
search.addEventListener('input', searchTable);
function searchTable() {
    tableRows.forEach((row, i) => {
        let tableData = row.textContent.toLowerCase();
        let searchData = search.value.toLowerCase();

        row.classList.toggle('hide', tableData.indexOf(searchData) < 0);
        row.style.setProperty('--delay', i / 25 + 's');
    });

    document.querySelectorAll('tbody tr:not(.hide)').forEach((visibleRow, i) => {
        visibleRow.style.backgroundColor = i % 2 == 0 ? 'transparent' : '#0000000b';
    });
}

// 2. Ordenar dados da tabela HTML
tableHeadings.forEach((head, i) => {
    let sortAsc = true;
    head.onclick = () => {
        tableHeadings.forEach((head) => head.classList.remove('active'));
        head.classList.add('active');

        document.querySelectorAll('td').forEach((td) => td.classList.remove('active'));
        tableRows.forEach((row) => {
            row.querySelectorAll('td')[i-1].classList.add('active');
        });

        head.classList.toggle('asc', sortAsc);
        sortAsc = head.classList.contains('asc') ? false : true;

        sortTable(i-1, sortAsc);
    };
});

function sortTable(column, sortAsc) {
    [...tableRows]
        .sort((a, b) => {
            let firstRow = a.querySelectorAll('td')[column].textContent.toLowerCase();
            let secondRow = b.querySelectorAll('td')[column].textContent.toLowerCase();

            return sortAsc ? (firstRow < secondRow ? -1 : 1) : firstRow < secondRow ? 1 : -1;
        })
        .forEach((sortedRow) => document.querySelector('tbody').appendChild(sortedRow));
}


function filtrarPorOnibusUm() {
    tableRows.forEach((row) => {
        const onibusColumn = row.querySelector('.central');
        const onibusValue = onibusColumn.textContent.trim();

        row.style.display = (onibusValue === '1') ? 'table-row' : 'none';
    });
}

