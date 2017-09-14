var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var PORT = process.env.PORT || 3000;

var multer = require('multer'); 

var Storage = multer.diskStorage({
    destination: function (req, file, callback) {
        callback(null, "img");
    },
    filename: function (req, file, callback) {
        callback(null, file.fieldname + "_" + Date.now() + "_" + file.originalname);
    }
});
var upload = multer({ storage: Storage }).array("imgUploader", 1); //Field name and max count 
app.get("/", function (req, res) { 
    res.sendFile(__dirname + "/index.html"); 
}); 
  
app.post("/api/Upload", function (req, res) { 
    upload(req, res, function (err) { 
        if (err) { 
            return res.end("Something went wrong!"); 
        } 
       // return res.end("File uploaded sucessfully!."); 
    }); 
}); 



var hotels = [{
    id: '',
    name: '',
    price: '',
    desc: ''
}];

app.use(express.static(__dirname));
app.use(bodyParser.json());


app.get('/api/besthotels', function(req, res) {
    console.log(req.query.city);
    res.send(hotels);
});

app.get('/api/toppromotions', function(req, res) {
    console.log(req.query.city);
    res.send("siema");



});







var currentId = 2;



app.get('/hotels', function(req, res) {
    res.send({ hotels: hotels });
});




app.post('/hotels', function(req, res) {
    var hotelName = req.body.name;
    var hotelPrice = req.body.price;
    var hotelDesc = req.body.desc;
    currentId++;

    hotels.push({
        id: currentId,
        name: hotelName,
        price: hotelPrice,
        desc: hotelDesc
    });

    res.send('Successfully created hotel!');
});



















app.put('/hotels/:id', function(req, res) {
    var id = req.params.id;
    var newName = req.body.newName;
    var newPrice = req.body.newPrice;
    var newDesc = req.body.newDesc;
    var found = false;

    hotels.forEach(function(hotel, index) {
        if (!found && hotel.id === Number(id)) {
            hotel.name = newName;
            hotel.price = newPrice;
            hotel.desc = newDesc;


        }
    });

    res.send('Succesfully updated hotel!');
});

app.delete('/hotels/:id', function(req, res) {
    var id = req.params.id;

    var found = false;

    hotels.forEach(function(hotel, index) {
        if (!found && hotel.id === Number(id)) {
            hotels.splice(index, 1);
        }
    });

    res.send('Successfully deleted hotel!');
});

app.listen(PORT, function() {
    console.log('Server listening on ' + PORT);
});
