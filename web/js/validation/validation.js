function validationFormSettingUpdate() {
    $('#validate-form-Setting-Update').validate({
        rules: {
            settingTitle: {
                required: true,
            },
            settingOrder: {
                required: true,
                number: true,
                min: 1
            }
        },
        messages: {
            settingTitle: {
                required: "Setting Title can not be empty!",
            },
            settingOrder: {
                required: "Setting Order can not be empty!",
                number: "Please enter the number!",
                min: "Setting Order must be > 0"
            }
        }
    });
}

function validationFormSettingAdd() {
    $('#validate-form-Setting-Add').validate({
        rules: {
            settingType: {
                required: true,
            },
            settingTitle: {
                required: true,
            },
            settingOrder: {
                required: true,
                number: true,
                min: 1
            }
        },
        messages: {
            settingType: {
                required: "Setting Type can not be empty!",
            },
            settingTitle: {
                required: "Setting Title can not be empty!",
            },
            settingOrder: {
                required: "Setting Order can not be empty!",
                number: "Please enter the number!",
                min: "Setting Order must be > 0"
            }
        }
    });
}

function validationFormClassSettingUpdate() {
    $('#validate-form-Class-Setting-Update').validate({
        rules: {
            settingTitle: {
                required: true,
            },
            settingOrder: {
                required: true,
                number: true,
                min: 1,
                max: 100,
            }
        },
        messages: {
            settingTitle: {
                required: "Class Setting Title can not be empty!",
            },
            settingOrder: {
                required: "Class Setting Order can not be empty!",
                number: "Please enter the number!",
                min: "Class Setting Order must be > 0",
                max: "Class Setting Order must be < 100"
            }
        },
    });


}


