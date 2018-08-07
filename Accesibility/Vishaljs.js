//"use strict";
//var t = 10,
//    interval = setInterval(function(){
//        if ( t > 0 ) {
//		console.log("Vishal");
//            console.log(t--);
//        } else {
//            console.log("BLAST OFF!");
//            phantom.exit();
//        }
//    }, 1000);
//////////////////////////////////////////////////////////////
//"use strict";
//console.log('Vishal Nihaniwal');
//phantom.exit();
////////////////////////////////////////////////////////////




// Set the default Foo header to "bar"
var test = pa11y({
    page: {
        headers: {
            Foo: 'bar'
        }
    }
});

// Run a test with the Foo header set to "bar"
test.run('http://www.example.com/', function(error, results) { /* ... */ });

// Run a test with the Foo header overridden
test.run('http://www.example.com/', {
    page: {
        headers: {
            Foo: 'hello'
        }
    }
}, function(error, results) { /* ... */ });
