function jsFormatNumber(number) {
    const formatter = new Intl.NumberFormat('en-US', {
        useGrouping: false,
        maximumFractionDigits: 2,
        minimumFractionDigits: 0,
    });
    return formatter.format(number);
}