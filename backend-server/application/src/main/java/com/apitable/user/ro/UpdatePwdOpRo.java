/*
 * APITable <https://github.com/apitable/apitable>
 * Copyright (C) 2022 APITable Ltd. <https://apitable.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.apitable.user.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import com.apitable.base.enums.ValidateType;

/**
 * <p>
 * Modify password request parameters
 * </p>
 */
@Data
@ApiModel("Modify password request parameters")
public class UpdatePwdOpRo {

    @ApiModelProperty(value = "Check Type", example = "sms_code")
    private ValidateType type;

    @ApiModelProperty(value = "Phone number/Email Verification Code", example = "123456", position = 2)
    private String code;

    @ApiModelProperty(value = "Password", example = "qwer1234", position = 3, required = true)
    private String password;
}
