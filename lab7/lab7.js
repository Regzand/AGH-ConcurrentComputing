class Fork {
	
	constructor(id){
		this.id = id;
		this.taken = false;
	}

	acquire(cb) {
		var self = this;
		var sleep = 1;

		var test = function(){
			if(self.taken){
				sleep *= 2;	
				setTimeout(test, sleep);
			} else {
				self.taken = true;
				cb();
			} 
		}
		
		test();
	}

	release() { 
		this.taken = false; 
	}

}

class Philosopher {

	constructor(id, f1, f2){
		this.id = id;
		this.f1 = f1;
		this.f2 = f2;
	}
	
	startNaive(count) {
		var self = this;

		var eat = function(i){
			if(i <= 0){
				console.log("[%d] Ended", self.id);
				return;
			}

			self.f1.acquire(function(){
				console.log("[%d] Acquired fork %d", self.id, self.f1.id);
				
				self.f2.acquire(function(){
					console.log("[%d] Acquired fork %d", self.id, self.f2.id);
					
					setTimeout(function(){

						self.f1.release();
						console.log("[%d] Released fork %d", self.id, self.f1.id);

						self.f2.release();
						console.log("[%d] Released fork %d", self.id, self.f2.id);

						eat(i-1);

					}, Math.random() * 1000);
				});
			});
		};

		eat(count);
	}

	startAsym(count) {
		var self = this;

		if(this.id % 2){
			var f1 = this.f1;
			var f2 = this.f2;
		} else {
			var f1 = this.f2;
			var f2 = this.f1;
		}
		
		var eat = function(i){
			if(i <= 0){
				console.log("[%d] Ended", self.id);
				return;
			}

			f1.acquire(function(){
				console.log("[%d] Acquired fork %d", self.id, f1.id);
				
				f2.acquire(function(){
					console.log("[%d] Acquired fork %d", self.id, f2.id);
					
					setTimeout(function(){

						f1.release();
						console.log("[%d] Released fork %d", self.id, f1.id);

						f2.release();
						console.log("[%d] Released fork %d", self.id, f2.id);

						eat(i-1);

					}, Math.random() * 1000);
				});
			});
		};

		eat(count);
	}

	startConductor(count, conductor) {
		var self = this;

		var eat = function(i){
			if(i <= 0){
				console.log("[%d] Ended", self.id);
				return;
			}

			conductor.acquire(self.id, function(){
				console.log("[%d] Acquired forks: %d and %d", self.id, self.f1.id, self.f2.id);

				setTimeout(function(){

					conductor.release();
					console.log("[%d] Released forks %d and %d", self.id, self.f1.id, self.f2.id);

					eat(i-1);

				}, Math.random() * 1000);
			});
		};

		eat(count);
	}
	
}

class Conductor {
	
	constructor(forks){
		this.forks = forks;
		this.queue = [];
	}

	acquire(i, cb) {
		var self = this;
		var sleep = 1;

		var test = function(){
			if(self.forks[(i) % self.forks.length].taken || [(i+1) % self.forks.length].taken){
				sleep *= 2;	
				setTimeout(test, sleep);
			} else {
				self.forks[(i) % self.forks.length] = true;
				self.forks[(i+1) % self.forks.length] = true;
				cb();
			} 
		}
		
		test();
	}

	release(i) { 
		this.forks[(i) % this.forks.length] = true;
		this.forks[(i+1) % this.forks.length] = true;
	}
	
}

const NAIVE = 0;
const ASYM = 1;
const CONDUCTOR = 2;

function testPhilosophers(N, M, type){
	var forks = [];
	var philosophers = [];

	for (var i = 0; i < N; i++)
		forks.push(new Fork(i));

	for (var i = 0; i < N; i++)
		philosophers.push(new Philosopher(i, forks[i % forks.length], forks[(i+1) % forks.length]));
	
	switch(type){
		case NAIVE:
			for (var i = 0; i < N; i++)
				philosophers[i].startNaive(M);
			break;

		case ASYM:
			for (var i = 0; i < N; i++)
				philosophers[i].startAsym(M);
			break;

		case CONDUCTOR:
			for (var i = 0; i < N; i++)
				philosophers[i].startConductor(M, new Conductor(forks));
			break;
	}
}

// ============================================ //
//	EXPORT										//
// ============================================ //
module.exports = {
	Fork: Fork,
	Philosopher: Philosopher,
	testPhilosophers: testPhilosophers,
	NAIVE: NAIVE,
	ASYM: ASYM,
	CONDUCTOR: CONDUCTOR
};

