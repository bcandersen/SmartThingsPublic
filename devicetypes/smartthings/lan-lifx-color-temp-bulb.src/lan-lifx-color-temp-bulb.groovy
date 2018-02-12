//THIS FILE IS PART OF THE NEW SUPER CONNECT APP FEATURE.
/**
 *  LAN LIFX Color Temperature Bulb
 *
 *  LIFX Color Temperature Bulb
 *
 *  Author: SmartThings
 */
metadata {
	definition(name: "LAN LIFX Color Temperature Bulb", namespace: "smartthings", author: "SmartThings", ocfDeviceType: "oic.d.light", runLocally: true, minHubCoreVersion: '000.019.00012', executeCommandsLocally: true) {
		capability "Switch Level"
		capability "Actuator"
		capability "Color Temperature"
		capability "Switch"
		capability "Refresh"
		capability "Sensor"
		capability "Health Check"
		capability "Light"

		command "setAdjustedColor"
		command "refresh"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles(scale: 2) {
		multiAttributeTile(name: "rich-control", type: "lighting", width: 6, height: 4, canChangeIcon: true) {
			tileAttribute("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label: '${name}', action: "switch.off", icon: "st.lights.philips.hue-single", backgroundColor: "#00A0DC", nextState: "turningOff"
				attributeState "off", label: '${name}', action: "switch.on", icon: "st.lights.philips.hue-single", backgroundColor: "#ffffff", nextState: "turningOn"
				attributeState "turningOn", label: '${name}', action: "switch.off", icon: "st.lights.philips.hue-single", backgroundColor: "#00A0DC", nextState: "turningOff"
				attributeState "turningOff", label: '${name}', action: "switch.on", icon: "st.lights.philips.hue-single", backgroundColor: "#ffffff", nextState: "turningOn"
			}
			tileAttribute("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action: "switch level.setLevel", range: "(0..100)"
			}
		}

		controlTile("colorTempSliderControl", "device.colorTemperature", "slider", width: 4, height: 2, inactiveLabel: false, range: "(1500..6500)") {
			state "colorTemperature", action: "color temperature.setColorTemperature"
		}

		valueTile("colorTemp", "device.colorTemperature", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "colorTemperature", label: '${currentValue} K'
		}

		standardTile("refresh", "device.refresh", height: 2, width: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: "", action: "refresh.refresh", icon: "st.secondary.refresh"
		}

		main(["rich-control"])
		details(["rich-control", "colorTempSliderControl", "colorTemp", "refresh"])
	}
}