/**
 *  Arduino Gateway Bedroom Fan Light
 *
 *  Copyright 2015 Chris Carde
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
metadata {
	definition (name: "Arduino Gateway: Bedroom Ceiling Light", namespace: "ccarde", author: "Chris Carde") {
		capability "Switch"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: '${currentValue}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			state "on", label: '${currentValue}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
		}
		standardTile("on", "device.switch", decoration: "flat") {
			state "default", label: 'On', action: "switch.on", backgroundColor: "#ffffff"
		}
		standardTile("off", "device.switch", decoration: "flat") {
			state "default", label: 'Off', action: "switch.off", backgroundColor: "#ffffff"
		}
        main "switch"
		details(["switch","on","off"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'switch' attribute

}

// handle commands
def on() {
	log.debug "Executing 'on'"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/arduino/light/on",
        headers: [
            HOST: "10.9.5.40:80"
        ]
    )
    sendEvent(name: "switch", value: "on")
    result
}

def off() {
	log.debug "Executing 'off'"
    def result = new physicalgraph.device.HubAction(
        method: "GET",
        path: "/arduino/light/off",
        headers: [
            HOST: "10.9.5.40:80"
        ]
    )
    sendEvent(name: "switch", value: "off")
    result
}


