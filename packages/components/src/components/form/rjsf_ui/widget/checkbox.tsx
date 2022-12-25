/**
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

import { WidgetProps } from '@rjsf/core';
import { Switch } from 'components/switch';
import React from 'react';
import styled from 'styled-components';

const CheckboxWrapper = styled.div`
  display: flex;
  cursor: pointer;  
  width: '100%';
  user-select: none;
  padding: 4px;
  border-radius: 4px;
`;

export const CheckboxWidget = (props: WidgetProps) => {
  return (
    <CheckboxWrapper onClick={() => props.onChange(!props.value)}>
      <Switch checked={props.value} /> <span style={{ paddingLeft: 8 }}>{props.schema.description}</span>
    </CheckboxWrapper>
  );
};