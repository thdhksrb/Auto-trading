document.getElementById("getAccounts").addEventListener("click", function() {
    $.ajax({
        url: "/myAsset",
        method: "GET",
        success: function(data) {
            var box = $("#dataBox");
            box.empty();

            $.each(data, function(index, item) {
                var text = item.currency + ": " + item.balance;
                var element = $("<p>").text(text);
                box.append(element);
            });

            console.log(data);
        },
        error: function(xhr, status, error) {
            
            console.error(error);
        }
    });
});
