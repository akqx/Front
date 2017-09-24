//-------------------------------------------------------------------------------
//FACTORY prattern
/////////////////////////////////////////////////////////////////////////////////
var peopleFactory = function(name, age, state) {
    // to samo : var temp=new Object;
    var temp = new Object;
    temp.age = age
    temp.name = name;
    temp.state = state;
    temp.printPerson = function() { console.log(this.name + "," + this.age + "," + this.state) };
    return temp;

};

var preson1 = peopleFactory('aga', '23', 'CS');
var preson2 = peopleFactory('kasia', '40', 'LA');

// preson1.printPerson();
// preson2.printPerson();


//----------------------------------------------------------------------------------
//CONSTRUCTOR pattern
/////////////////////////////////////////////////////////////////////////////////
var peopleConstructor = function(name, age, state) {
    this.name = name;
    this.age = age;
    this.state = state;

    this.printPerson = function() {
        console.log(this.name + "," + this.age + "," + this.state)
    };
};

var person1 = new peopleConstructor('aga', '23', 'CS');
var person2 = new peopleConstructor('Kasia', '40', 'LA');

// preson1.printPerson();
// preson2.printPerson();

//--------------------------------------------------------------------------------
//PROTOTYPE pattern
///////////////////////////////////////////////////////////////////////////////////
var peopleProto = function() {

};

peopleProto.prototype.age = 0;
peopleProto.prototype.name = 'no name';
peopleProto.prototype.state = 'no name';

peopleProto.prototype.printPerson = function() {
    console.log(this.name + "," + this.age + "," + this.state);
};

var person1 = new peopleProto();

// person1.name='aga';
person1.age = 23;
person1.state = 'CS';
//true or false, szukamy czy jest name w tym obiekcie
// // console.log('name' in person1);
// // //Czy dostał ta nazwę czy sam ją wymyślił 
// // console.log(person1.hasOwnProperty('name'));
// person1.printPerson();

//--------------------------------------------------------------------------------
//DYNAMIC PROTOTYPE PATTERN
///////////////////////////////////////////////////////////////////////////////////

var peopleDynamic = function(name, age, state) {
    this.age = age;
    this.name = name;
    this.state = state;
    //utworzona przy pierwszym obiekcie, później nie tworzy sie ponownie
    if (typeof this.printPerson !== 'function') {
        peopleDynamic.prototype.printPerson = function() {
            console.log(this.name + "," + this.age + "," + this.state);
        };
    }

};



var person1 = new peopleDynamic('aga','23','CS');


person1.printPerson();