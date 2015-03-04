/**
 * Copyright (c) 2007-2014 Kaazing Corporation. All rights reserved.
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.kaazing.gateway.transport.sse.bridge.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.kaazing.mina.core.buffer.IoBufferAllocatorEx;
import org.kaazing.mina.core.session.IoSessionEx;
import org.kaazing.mina.filter.codec.ProtocolCodecFilter;

public class SseConnectCodecFilter extends ProtocolCodecFilter {
	public SseConnectCodecFilter() {
		super(new SseConnectCodecFactory());
	}	

	private static class SseConnectCodecFactory implements ProtocolCodecFactory {
	    
	    public ProtocolEncoder getEncoder(IoSession session) {
	        return new ProtocolEncoderAdapter() {
	            public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
	            }
	          };
	    }
	    
	    public ProtocolDecoder getDecoder(IoSession session) {
	        IoSessionEx sessionEx = (IoSessionEx) session;
	        IoBufferAllocatorEx<?> allocator = sessionEx.getBufferAllocator();
	        return new SseDecoder(allocator);
	    }
	}
}