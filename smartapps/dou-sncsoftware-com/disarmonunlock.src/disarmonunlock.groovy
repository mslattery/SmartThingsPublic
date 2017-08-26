/**
 *  DisarmOnUnlcok
 *
 *  Copyright 2017 Michael Slattery
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "DisarmOnUnlock",
    namespace: "dou.sncsoftware.com",
    author: "Michael Slattery",
    description: "Disarm SHM based on lock unlock",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Unlock when unlock detected:") {
		input "thelock", "capability.lock", required: true
	}
}

def installed() {
	initialize()
}

def updated() {
	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(thelock, "lock.unlocked", unlockDetectedHandler)
}

def unlockDetectedHandler(evt) {   
    sendLocationEvent(name: "alarmSystemStatus", value: "off") 
}